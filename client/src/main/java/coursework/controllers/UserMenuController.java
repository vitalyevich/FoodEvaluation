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

import javax.swing.event.AncestorEvent;

public class UserMenuController {

    private Notification notification = new Notification();
    private Open open = new Open();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonProduct;

    @FXML
    private Button buttonService;

    @FXML
    private Button buttonExit;

    @FXML
    void onClicked_Exit(MouseEvent event) {
        boolean check = notification.showConfirmation("Выход", "Вы действительно желаете выйти?", "Для продолжения нажмите кнопку!");
        if (check) {
            open.initFXML(buttonExit, "/coursework/view.fxml", "Программа оценки качества продукции БГУИР", 1);
        }
    }

    private Session session = Session.getInstance();
    private AnchorPane anchorPane;
    @FXML
    void onClicked_Product(MouseEvent event) throws IOException {
        session.getAnchorPage().getChildren().setAll(anchorPane);
        session.getTest().getChildren().add(open.initMenu("/coursework/controllers/testOrganoleptic.fxml"));
    }

    @FXML
    void onClicked_Service(MouseEvent event) throws IOException {
        session.getAnchorPage().getChildren().setAll(anchorPane);
        session.getTest().getChildren().add(open.initMenu("/coursework/controllers/testServqual.fxml"));
    }

    @FXML
    void initialize() throws IOException {
        anchorPane = open.initMenu("/coursework/controllers/test.fxml");
    }
}
