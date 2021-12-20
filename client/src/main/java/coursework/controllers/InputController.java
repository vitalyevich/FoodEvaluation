package coursework.controllers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InputController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button buttonInput;

    @FXML
    private Button buttonReg;

    @FXML
    private Label error;

    @FXML
    void onClicked_Input(MouseEvent event) throws IOException {

        OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
        try {
            int roleId = operationWithUser.findAccess(login.getText(), password.getText());

            Session session = Session.getInstance();

            session.setRoleId(roleId);

            switch (roleId) {
                case -1: {
                    error.setText("Данный пользователь заблокирован!");
                    break;
                }
                case 0: {
                    error.setText("Неверный логин или пароль!");
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    session.setUserId(operationWithUser.findUser(login.getText()));

                    session.getBaseAnchor().getChildren().addAll(open.initMenu("/coursework/controllers/menu.fxml"));
                    break;
                }
            }
        } catch (NullPointerException e) {
            error.setText("Соединение с сервером не установлено!");
        }
    }

    private AnchorPane anchorPane;

    private Open open = new Open();

    @FXML
    void onClicked_Reg(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/reg.fxml");
        Session singleton = Session.getInstance();
        singleton.getAnchorView().getChildren().setAll(anchorPane);
    }

    @FXML
    void initialize() {

    }
}
