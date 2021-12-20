package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class TestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchor;

    private Session singleton = Session.getInstance();

    @FXML
    void initialize() throws IOException {
        singleton.setTest(anchor);
    }
}
