package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.Access;
import coursework.models.Product;
import coursework.models.ProductType;
import coursework.operations.Notification;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EditProductController {

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
    private Button buttonEdit;

    private BillingClient client = new BillingClient();

    private Notification notification = new Notification();

    @FXML
    void onClicked_Edit(MouseEvent event) throws RemoteException {

        try {
            if (type.getValue().equals("") || name.getText().equals("") || desc.getText().equals("") ||
                    price.getText().equals("")) {
                throw new NullPointerException();
            }

            Long.parseLong(price.getText());

            boolean check = notification.showConfirmation("Изменение продукта", "Вы действительно желаете " +
                    "изменить данный товар?", "Для того, чтобы продолжить, нажмите кнопку!");
            if (check) {
                ProductType productType = new ProductType();
                productType.setId(searchTypeId(type.getValue()));

                try {
                    client.editProduct(new Product(product.getId(),productType, name.getText(), desc.getText(), Long.parseLong(price.getText())));
                    notification.showAlertWithoutHeaderText("Изменение продукта", "Продукт успешно обновлен!");
                    buttonEdit.getScene().getWindow().hide();
                } catch (RemoteException e) {
                    notification.showAlertWithoutWarning("Изменение продукта", "Соединение с сервером потеряно!");
                }
            }
        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Изменение продукта", "Не все поля были заполнены!");
        } catch (NumberFormatException e) {
            notification.showAlertWithoutWarning("Изменение продукта", "Некорректные данные!");
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
    private Session session = Session.getInstance();
    private Product product;
    @FXML
    void initialize() throws RemoteException {
        productTypeList = client.getListTypeProduct();

        for (int i = 0; i < productTypeList.size(); i++ ) {
            type.getItems().add(productTypeList.get(i).getProductName());
        }
        product = session.getProduct();
        name.setText(product.getProductName());
        desc.setText(product.getMenuDescription());
        price.setText(product.getPrice() + "");
        type.getSelectionModel().select(product.getType().getId() - 1);
    }
}
