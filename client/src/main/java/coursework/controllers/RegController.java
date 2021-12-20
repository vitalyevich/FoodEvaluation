package coursework.controllers;

import java.io.IOException;
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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonReg;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField age;

    @FXML
    private ComboBox<Character> gender;

    @FXML
    private ComboBox<String> role;

    @FXML
    private Button buttonCancel;

    private AnchorPane anchorPane;

    private Open open = new Open();

    @FXML
    private Label error;

    @FXML
    void onClicked_Cancel(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/input.fxml");
        Session.getInstance().getAnchorView().getChildren().setAll(anchorPane);
    }

    private Notification notification = new Notification();
    @FXML
    void onClicked_Reg(MouseEvent event) throws IOException {

        try {
            if (role.getItems().equals("") || gender.getItems().equals("") || login.getText().equals("") ||
                    password.getText().equals("") || age.getText().equals("")) {
                throw new NullPointerException();
            }

            Integer.parseInt(age.getText());

            boolean check = notification.showConfirmation("Регистрация","Вы точно хотите зарегистрироваться?","Пожалуйста," +
                    " подтвердите регистрацию!");

            if (check) {
                int roleId = searchRoleId(role.getValue());
                int accessStatus = 1;

                if (roleId == 3) {
                    accessStatus = 0;
                }
                Role role = new Role();
                role.setId(roleId);

                operationWithUser.addNewUser(new User(login.getText(), Integer.parseInt(age.getText()), gender.getValue()),
                        new Access(1,role, BCrypt.hashpw(password.getText().trim(), BCrypt.gensalt(12)), accessStatus));

                notification.showAlertWithoutHeaderText("Регистрация", "Вы успешно зарегистрировались в системе!");

                anchorPane = open.initMenu("/coursework/controllers/input.fxml");
                Session.getInstance().getAnchorView().getChildren().setAll(anchorPane);
            }

        } catch (NullPointerException e) {
            error.setText("Не все поля были заполнены!");
        } catch (NumberFormatException e) {
            error.setText("Некорректный ввод данных!");
        } catch (SQLException e) {
            error.setText("Данный логин уже занят!");
        } catch (RemoteException e) {
            error.setText("Соединение с сервером потеряно!");
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

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    @FXML
    void initialize() {
        gender.getItems().add('М');
        gender.getItems().add('Ж');

        role.getItems().add("Пользователь");
        role.getItems().add("Эксперт");
    }
}
