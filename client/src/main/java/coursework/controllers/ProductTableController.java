package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.models.Product;
import coursework.operations.Notification;
import coursework.operations.Session;
import coursework.rmi.BillingClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ProductTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, Integer> numberColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> typeColumn;

    @FXML
    private TableColumn<Product, String> descColumn;

    @FXML
    private TableColumn<Product, Long> priceColumn;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDel;

    @FXML
    void onClicked_Table(MouseEvent event) {
        if (tableProduct.getSelectionModel().getSelectedItem() != null) {
            buttonDel.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    @FXML
    void onClicked_Add(MouseEvent event) {
        open.initFXML(buttonEdit,"/coursework/controllers/addProduct.fxml","Добавление продукта",0);
    }

    private BillingClient client = new BillingClient();
    private Notification notification = new Notification();
    @FXML
    void onClicked_Del(MouseEvent event) throws RemoteException {
        boolean check = notification.showConfirmation("Удаление продукта", "Вы действительно желаете " +
                "удалить данный товар?", "Для того, чтобы продолжить, нажмите кнопку!");
        if (check) {
            client.delProduct(tableProduct.getSelectionModel().getSelectedItem().getId());
            tableProduct.getItems().remove(tableProduct.getSelectionModel().getSelectedItem());
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    private Open open = new Open();
    private Session session = Session.getInstance();
    @FXML
    void onClicked_Edit(MouseEvent event) {
        session.setProduct(tableProduct.getSelectionModel().getSelectedItem());
        open.initFXML(buttonEdit,"/coursework/controllers/editProduct.fxml","Редактирование продукта",0);
    }

    @FXML
    void initialize() throws RemoteException {

        BillingClient client = new BillingClient();
        productsObservableList = FXCollections.observableArrayList(client.getListProduct());
        fillingTable(productsObservableList);
    }

    private ObservableList<Product> productsObservableList;

    private void fillingTable(ObservableList<Product> products) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeProduct"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("menuDescription"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableProduct.setItems(products);
    }
}
