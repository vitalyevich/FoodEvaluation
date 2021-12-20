package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.*;
import coursework.operations.Notification;
import coursework.operations.OperationWithTest;
import coursework.operations.OperationWithTestImpl;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class TestOrganolepticController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> product;

    @FXML
    private ComboBox<Integer> visual;

    @FXML
    private ComboBox<Integer> tactile;

    @FXML
    private ComboBox<Integer> olfactory;

    @FXML
    private ComboBox<Integer> gustatory;

    @FXML
    private Button buttonSave;

    private Notification notification = new Notification();

    private OperationWithTestImpl operationWithTest = new OperationWithTestImpl();
    private Session session = Session.getInstance();

    @FXML
    void onClicked_Save(MouseEvent event) {

        try {
            if (!type.getValue().equals("") && !product.getValue().equals("") && !visual.getValue().equals("") &&
                    !tactile.getValue().equals("") && !olfactory.getValue().equals("") && !gustatory.getValue().equals("")) {

                boolean check = notification.showConfirmation("Тест", "Вы подтверждаете сохранение теста?","Пожалуйста, нажмите кнопку!");
                if (check) {
                    Product product1 = new Product();
                    product1.setId(searchProductId(product.getValue()));
                    Archive archive = new Archive();
                    archive.setArchiveDate(LocalDate.now());
                    archive.setArchiveTime(LocalTime.now());

                    User user = new User();
                    user.setId(session.getUserId());

                    operationWithTest.addTestOrganoleptic(new Organoleptic(product1, archive, user,
                            visual.getValue(), tactile.getValue(), olfactory.getValue(), gustatory.getValue()));

                    notification.showAlertWithoutHeaderText("Тест", "Успешное добавление результатов!");
                }
            }
            else {
                notification.showAlertWithoutWarning("Тест", "Вы не заполнили все данные, пожалуйста, проверьте еще раз!");
            }
        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Тест", "Вы не заполнили все данные, пожалуйста, проверьте еще раз!");
        } catch (RemoteException e) {
            notification.showAlertWithoutWarning("Тест", "Соединение с сервером потеряно!");
        }
    }

    private int searchProductId(String name) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductName().equals(name)) {
                return products.get(i).getId();
            }
        }
        return 0;
    }

    @FXML
    void onAction_Type(ActionEvent event) {
        product.getItems().clear();
        for (int i = 0; i < products.size(); i++) {
           if (products.get(i).getType().getProductName().equals(type.getValue())) {
               product.getItems().add(products.get(i).getProductName());
           }
        }
    }

    private BillingClient client = new BillingClient();
    private List<Product> products;
    private List<ProductType> productTypeList;
    @FXML
    void initialize() throws RemoteException {

        for (int i = 1; i <= 10; i++) {
            visual.getItems().add(i);
            tactile.getItems().add(i);
            olfactory.getItems().add(i);
            gustatory.getItems().add(i);
        }

        products = client.getListProduct();
        productTypeList = client.getListTypeProduct();

        for (int i = 0; i < productTypeList.size(); i++) {
            type.getItems().add(productTypeList.get(i).getProductName());
        }
    }
}
