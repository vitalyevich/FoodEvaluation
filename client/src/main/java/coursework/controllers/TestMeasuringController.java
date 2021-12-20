package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.*;
import coursework.operations.Notification;
import coursework.operations.OperationWithTestImpl;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class TestMeasuringController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> product;

    @FXML
    private ComboBox<String> weight;

    @FXML
    private ComboBox<String> ingridient;

    @FXML
    private ComboBox<String> ready;

    @FXML
    private ComboBox<String> structute;

    @FXML
    private ComboBox<String> quantity;

    @FXML
    private Button buttonSave;

    @FXML
    void onAction_Type(ActionEvent event) {
        product.getItems().clear();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getType().getProductName().equals(type.getValue())) {
                product.getItems().add(products.get(i).getProductName());
            }
        }
    }

    private Notification notification = new Notification();
    private OperationWithTestImpl operationWithTest = new OperationWithTestImpl();
    private Session session = Session.getInstance();
    @FXML
    void onClicked_Save(MouseEvent event) {
        try {
            if (!type.getValue().equals("") && !product.getValue().equals("") && !weight.getValue().equals("") &&
                    !ingridient.getValue().equals("") && !ready.getValue().equals("") && !structute.getValue().equals("") ||
            !quantity.getValue().equals("")) {

                boolean check = notification.showConfirmation("Тест", "Вы подтверждаете сохранение теста?","Пожалуйста, нажмите кнопку!");
                if (check) {
                    Product product1 = new Product();
                    product1.setId(searchProductId(product.getValue()));
                    Archive archive = new Archive();
                    archive.setArchiveDate(LocalDate.now());
                    archive.setArchiveTime(LocalTime.now());

                    User user = new User();
                    user.setId(session.getUserId());


                    operationWithTest.addTestMeasuring(new Measuring(product1, archive, user,
                            searchNumber(weight.getValue()), searchNumber(ingridient.getValue()), searchNumber(ready.getValue()),
                            searchNumber(structute.getValue()),searchNumber(quantity.getValue())));

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

    private int searchNumber(String choice) {
        if (choice.equals("Да")) {
            return 1;
        }
        else {
            return 0;
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

    private BillingClient client = new BillingClient();
    private List<Product> products;
    private List<ProductType> productTypeList;

    @FXML
    void initialize() throws RemoteException {
        String [] str = {"Да","Нет"};
        weight.getItems().addAll(str);
        ingridient.getItems().addAll(str);
        ready.getItems().addAll(str);
        structute.getItems().addAll(str);
        quantity.getItems().addAll(str);

        products = client.getListProduct();
        productTypeList = client.getListTypeProduct();

        for (int i = 0; i < productTypeList.size(); i++) {
            type.getItems().add(productTypeList.get(i).getProductName());
        }
    }
}


