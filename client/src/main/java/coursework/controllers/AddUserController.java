package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.models.Access;
import coursework.models.Role;
import coursework.models.User;
import coursework.operations.Notification;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

public class AddUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private TextField age;

    @FXML
    private ComboBox<Character> gender;

    @FXML
    private ComboBox<String> role;

    @FXML
    private Button buttonAdd;

    private Notification notification = new Notification();
    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    private Open open = new Open();
    @FXML
    void onClicked_Add(MouseEvent event) throws SQLException, RemoteException {
        try {
            if (role.getItems().equals("") || gender.getItems().equals("") || login.getText().equals("") ||
                    password.getText().equals("") || age.getText().equals("")) {
                throw new NullPointerException();
            }

            Integer.parseInt(age.getText());

            boolean check = notification.showConfirmation("Добавление пользователя","Вы точно хотите добавить?","Пожалуйста," +
                    " нажмите на кнопку!");

            if (check) {
                int roleId = searchRoleId(role.getValue());
                int accessStatus = 1;

                Role role = new Role();
                role.setId(roleId);

                operationWithUser.addNewUser(new User(login.getText(), Integer.parseInt(age.getText()), gender.getValue()),
                        new Access(1,role, BCrypt.hashpw(password.getText().trim(), BCrypt.gensalt(12)), accessStatus));

                notification.showAlertWithoutHeaderText("Добавление пользователя", "Вы успешно добавили пользователя в систему!");
                buttonAdd.getScene().getWindow().hide();
            }

        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Добавление пользователя","Не все поля были заполнены!");
        } catch (NumberFormatException e) {
            notification.showAlertWithoutWarning("Добавление пользователя","Некорректный ввод данных!");
        } catch (SQLException e) {
            notification.showAlertWithoutWarning("Добавление пользователя","Данный логин уже занят!");
        } catch (RemoteException e) {
            notification.showAlertWithoutWarning("Добавление пользователя","Соединение с сервером потеряно!");
        }
      }

    private int searchRoleId(String role) {
        if (role.equals("Пользователь")) {
            return 2;
        }
        else {
            return 3;
        }
    }

    @FXML
    void initialize() {

        gender.getItems().add('М');
        gender.getItems().add('Ж');

        role.getItems().add("Пользователь");
        role.getItems().add("Эксперт");
    }
}
