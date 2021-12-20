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

public class ExpertMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonProduct1;

    @FXML
    private Button buttonProduct2;

    @FXML
    private Button buttonService;

    @FXML
    private Button buttonExit;

    private Notification notification = new Notification();

    @FXML
    void onClicked_Exit(MouseEvent event) {
        boolean check = notification.showConfirmation("Выход", "Вы действительно желаете выйти?", "Для продолжения нажмите кнопку!");
        if (check) {
            open.initFXML(buttonExit, "/coursework/view.fxml", "Программа оценки качества продукции БГУИР", 1);
        }
    }

    private AnchorPane anchorPane = new AnchorPane();
    private Open open = new Open();
    private Session session = Session.getInstance();
    @FXML
    void onClicked_Product1(MouseEvent event) throws IOException {
        session.getAnchorPage().getChildren().setAll(anchorPane);
        session.getTest().getChildren().add(open.initMenu("/coursework/controllers/testOrganoleptic.fxml"));
    }

    @FXML
    void onClicked_Product2(MouseEvent event) throws IOException {
        session.getAnchorPage().getChildren().setAll(anchorPane);
        session.getTest().getChildren().add(open.initMenu("/coursework/controllers/testMeasuring.fxml"));
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
