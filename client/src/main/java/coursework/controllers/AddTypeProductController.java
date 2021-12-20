package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import coursework.models.ProductType;
import coursework.operations.Notification;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddTypeProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private Button buttonAdd;

    private Notification notification = new Notification();

    private BillingClient client = new BillingClient();
    @FXML
    void onClicked_Add(MouseEvent event) {
        if(name.getText().equals("")) {
            notification.showAlertWithoutWarning("Добавление типа продукта", "Данные не заполнены!");
        } else {
            boolean check = notification.showConfirmation("Добавление типа продукта", "Вы подтверждаете добавление?",
                    "Для продолжения нажмите на кнопку!");
            if (check) {
                try {
                    client.addNewTypeProduct(new ProductType(name.getText()));
                    notification.showAlertWithoutHeaderText("Добавление типа продукта", "Успешное добавление данных!");
                    buttonAdd.getScene().getWindow().hide();
                } catch (SQLException e) {
                    notification.showAlertWithoutWarning("Добавление типа продукта", "Данный тип продукта уже существует!");
                } catch (RemoteException e) {
                    notification.showAlertWithoutWarning("Добавление типа продукта", "Потеряно соединение с сервером!");
                }
            }
        }
    }

    @FXML
    void initialize() {

    }
}
