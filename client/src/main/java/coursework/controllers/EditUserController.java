package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import coursework.models.Access;
import coursework.models.Role;
import coursework.models.User;
import coursework.operations.Notification;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.mindrot.jbcrypt.BCrypt;

public class EditUserController {

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
    private ComboBox<String> access;

    @FXML
    private Button buttonEdit;

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    private Notification notification = new Notification();

    @FXML
    void onClicked_Edit(MouseEvent event) {
        try {
            if (role.getItems().equals("") || gender.getItems().equals("") || login.getText().equals("") ||
                    age.getText().equals("")) {
                throw new NullPointerException();
            }

            Integer.parseInt(age.getText());

            boolean check = notification.showConfirmation("Изменение пользователя","Вы точно хотите изменить?","Пожалуйста," +
                    " нажмите на кнопку!");

            if (check) {
                int roleId = searchRoleId(role.getValue());

                Role role = new Role();
                role.setId(roleId);

                String password1 = access1.getPassword();

                if(!password.getText().equals("")) {
                    password1 = password.getText();
                }

                int status = searchAccessId(access.getValue());
                //EDIT
                operationWithUser.editUser(new User(access1.getUser().getId(), login.getText(),Integer.parseInt(age.getText()),gender.getValue()),
                        new Access(access1.getUser().getId(), role, password1, status));

                notification.showAlertWithoutHeaderText("Изменение пользователя", "Вы успешно изменили пользователя в системе!");
                buttonEdit.getScene().getWindow().hide();
            }

        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Изменение пользователя","Не все поля были заполнены!");
        } catch (NumberFormatException e) {
            notification.showAlertWithoutWarning("Изменение пользователя","Некорректный ввод данных!");
        } catch (RemoteException e) {
            notification.showAlertWithoutWarning("Изменение пользователя","Соединение с сервером потеряно!");
        }
    }

    private Session session = Session.getInstance();
    private Access access1;
    @FXML
    void initialize() {

        gender.getItems().add('М');
        gender.getItems().add('Ж');

        role.getItems().add("Пользователь");
        role.getItems().add("Эксперт");

        access.getItems().add("Заблокирован");
        access.getItems().add("Не подтвержден");
        access.getItems().add("Подтвержден");

        access1 = session.getAccess();
        login.setText(access1.getUser().getLoginName());
        age.setText(access1.getUser().getAgeNum() + "");
        gender.getSelectionModel().select(searchGenderId(access1.getUser().getGender()));
        role.getSelectionModel().select(searchRoleId(access1.getRole().getRoleName()));
        access.getSelectionModel().select(searchAccessId(access1.getAccessStatus()));
    }

    private int searchAccessId(int id) {
        if (id == -1) {
            return 0;
        }
        else if (id == 0) {
            return 1;
        }
        return 2;
    }

    private int searchAccessId(String name) {
        if (name.equals("Заблокирован")) {
            return -1;
        }
        else if (name.equals("Не подтвержден")) {
            return 0;
        }
        return 1;
    }

    private int searchRoleId(String name) {
        if (name.equals("Пользователь")) {
            return 2;
        }
        return 3;
    }

    private int searchGenderId(Character name) {
        if(name.equals('М')) {
            return 0;
        }
        return 1;
    }

}
