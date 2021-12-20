package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorMenu;

    @FXML
    private AnchorPane anchorView;

    private Open open = new Open();
    @FXML
    void initialize() throws IOException {

        Session singleton = Session.getInstance();
        int roleId = singleton.getRoleId();

        switch (roleId) {
            case 1: {
                anchorMenu.getChildren().setAll(open.initMenu("/coursework/controllers/adminMenu.fxml"));
                break;
            }
            case 2: {
                anchorMenu.getChildren().setAll(open.initMenu("/coursework/controllers/userMenu.fxml"));
                break;
            }
            case 3: {
                anchorMenu.getChildren().setAll(open.initMenu("/coursework/controllers/expertMenu.fxml"));
                break;
            }
        }
        singleton.setAnchorPage(anchorView);
    }
}

