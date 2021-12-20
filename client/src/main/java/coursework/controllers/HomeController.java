package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonLearn;

    private AnchorPane anchorPane;
    private Open open = new Open();

    @FXML
    void onClicked_Learn(MouseEvent event) throws IOException {
        Session singleton = Session.getInstance();
        singleton.getAnchorView().getChildren().setAll(open.initMenu("/coursework/controllers/learn.fxml"));
        singleton.getButtonLearn();
    }

    @FXML
    void initialize() {

    }
}
