package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import coursework.models.Access;
import coursework.models.ProductType;
import coursework.operations.Notification;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditTypeProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private Button buttonSave;

    private BillingClient client = new BillingClient();
    private Notification notification = new Notification();

    @FXML
    void onClicked_Save(MouseEvent event) {
        if(name.getText().equals("")) {
            notification.showAlertWithoutWarning("Изменение типа продукта", "Данные не заполнены!");
        } else {
            boolean check = notification.showConfirmation("Изменение типа продукта", "Вы подтверждаете изменение?",
                    "Для продолжения нажмите на кнопку!");
            if (check) {
                try {
                    client.editTypeProduct(new ProductType(productType.getId(), name.getText()));
                    notification.showAlertWithoutHeaderText("Изменение типа продукта", "Успешное обновление данных!");
                    buttonSave.getScene().getWindow().hide();
                  } catch (RemoteException e) {
                    notification.showAlertWithoutWarning("Изменение типа продукта", "Потеряно соединение с сервером!");
                }
            }
        }
    }

    private Session session = Session.getInstance();
    private ProductType productType;

    @FXML
    void initialize() {
        productType = session.getProductType();
        name.setText(productType.getProductName());
    }
}
