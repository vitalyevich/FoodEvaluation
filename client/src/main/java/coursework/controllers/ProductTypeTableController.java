package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.models.ProductType;
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

public class ProductTypeTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<ProductType> tableProductType;

    @FXML
    private TableColumn<ProductType, Integer> numberColumn;

    @FXML
    private TableColumn<ProductType, String> nameColumn;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonDel;

    @FXML
    void onClicked_Add(MouseEvent event) {
        open.initFXML(buttonEdit,"/coursework/controllers/addTypeProduct.fxml","Добавление типа продукта",0);
    }

    private Notification notification = new Notification();
    @FXML
    void onClicked_Del(MouseEvent event) throws RemoteException {

        boolean check = notification.showConfirmation("Удаление типа продукта", "Вы действительно желаете " +
                "удалить данный тип товара?", "Для того, чтобы продолжить, нажмите кнопку!");
        if (check) {
            client.delTypeProduct(tableProductType.getSelectionModel().getSelectedItem().getId());
            tableProductType.getItems().remove(tableProductType.getSelectionModel().getSelectedItem());
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    private Open open = new Open();
    private Session session = Session.getInstance();
    @FXML
    void onClicked_Edit(MouseEvent event) {
        session.setProductType(tableProductType.getSelectionModel().getSelectedItem());
        open.initFXML(buttonEdit,"/coursework/controllers/editTypeProduct.fxml","Редактирование типа продукта",0);
    }

    @FXML
    void onClicked_Table(MouseEvent event) {
        if (tableProductType.getSelectionModel().getSelectedItem() != null) {
            buttonDel.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    private ObservableList<ProductType> productTypes = FXCollections.observableArrayList();

    private BillingClient client = new BillingClient();
    @FXML
    void initialize() throws RemoteException {

        productTypes = FXCollections.observableArrayList(client.getListTypeProduct());
        fillingTable(productTypes);
    }

    private void fillingTable(ObservableList<ProductType> productTypes) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableProductType.setItems(productTypes);
    }
}
