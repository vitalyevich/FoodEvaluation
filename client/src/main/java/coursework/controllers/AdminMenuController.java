package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.operations.Notification;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AdminMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonUser;

    @FXML
    private Button buttonProduct;

    @FXML
    private Button buttonStats;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonType;

    private AnchorPane anchorPane = new AnchorPane();

    private Open open = new Open();

    @FXML
    void onClicked_Type(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/typeProductTable.fxml");
        session.getAnchorPage().getChildren().setAll(anchorPane);
    }

    private Notification notification = new Notification();
    @FXML
    void onClicked_Exit(MouseEvent event) throws IOException {

        boolean check = notification.showConfirmation("Выход", "Вы действительно желаете выйти?", "Для продолжения нажмите кнопку!");
        if (check) {
            session.getBaseAnchor().getChildren().addAll(open.initMenu("/coursework/view.fxml"));
        }
    }

    private Session session = Session.getInstance();
    @FXML
    void onClicked_Product(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/productTable.fxml");
        session.getAnchorPage().getChildren().setAll(anchorPane);
    }

    @FXML
    void onClicked_Stats(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/stats.fxml");
        session.getAnchorPage().getChildren().setAll(anchorPane);
    }

    @FXML
    void onClicked_User(MouseEvent event) throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/userTable.fxml");
        session.getAnchorPage().getChildren().setAll(anchorPane);
    }

    @FXML
    void initialize() {
    }
}
