package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.Product;
import coursework.models.ProductType;
import coursework.operations.Notification;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField name;

    @FXML
    private TextField desc;

    @FXML
    private TextField price;

    @FXML
    private Button buttonAdd;

    private BillingClient client = new BillingClient();

    private Notification notification = new Notification();
    @FXML
    void onClicked_Add(MouseEvent event) {

        try {
            if (type.getValue().equals("") || name.getText().equals("") || desc.getText().equals("") ||
                    price.getText().equals("")) {
                throw new NullPointerException();
            }

            Long.parseLong(price.getText());

            boolean check = notification.showConfirmation("Добавление продукта", "Вы действительно желаете " +
                    "добавить данный товар?", "Для того, чтобы продолжить, нажмите кнопку!");
            if (check) {
                ProductType productType = new ProductType();
                productType.setId(searchTypeId(type.getValue()));
                try {
                    client.addNewProduct(new Product(productType, name.getText(), desc.getText(), Long.parseLong(price.getText())));
                    notification.showAlertWithoutHeaderText("Добавление продукта", "Продукт успешно добавлен!");
                    buttonAdd.getScene().getWindow().hide();
                } catch (SQLException e) {
                    notification.showAlertWithoutWarning("Добавление продукта", "Данный продукт уже существует!");
                } catch (RemoteException e) {
                    notification.showAlertWithoutWarning("Добавление продукта", "Соединение с сервером потеряно!");
                }
            }
        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Добавление продукта", "Не все поля были заполнены!");
        } catch (NumberFormatException e) {
            notification.showAlertWithoutWarning("Добавление продукта", "Некорректные данные!");
        }
    }

    private int searchTypeId(String name) {
        for (int i = 0; i < productTypeList.size(); i++ ) {
            if (productTypeList.get(i).getProductName().equals(name)) {
                return productTypeList.get(i).getId();
            }
        }
        return 0;
    }

    private List<ProductType> productTypeList;
    @FXML
    void initialize() throws RemoteException {

        productTypeList = client.getListTypeProduct();

        for (int i = 0; i < productTypeList.size(); i++ ) {
            type.getItems().add(productTypeList.get(i).getProductName());
        }
    }
}
