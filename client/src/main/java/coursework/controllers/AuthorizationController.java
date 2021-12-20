package coursework.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane baseAnchor;

    @FXML
    private URL location;

    @FXML
    private ImageView image;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonContacts;

    @FXML
    private Button buttonLearn;

    @FXML
    private Button buttonInput;

    @FXML
    private AnchorPane anchor;

    private Open open = new Open();

    private final String BUTTON_COLOR = "-fx-text-fill: black; -fx-background-radius: 15; -fx-background-color: transparent;";

    private void setColorButton(Button button) {

        buttonHome.setStyle(BUTTON_COLOR);
        buttonInput.setStyle(BUTTON_COLOR);
        buttonContacts.setStyle(BUTTON_COLOR);
        buttonLearn.setStyle(BUTTON_COLOR);
        button.setStyle("-fx-background-color:  #6495ED; -fx-text-fill: white; -fx-background-radius: 15");
    }

    @FXML
    void OnClicked_Input(MouseEvent event) throws IOException {
        setColorButton(buttonInput);
        anchor.getChildren().setAll(open.initMenu("/coursework/controllers/input.fxml"));
    }

    @FXML
    void onClicked_Contacts(MouseEvent event) throws IOException {
        setColorButton(buttonContacts);
        anchor.getChildren().setAll(open.initMenu("/coursework/controllers/contacts.fxml"));
    }

    @FXML
    void onClicked_Home(MouseEvent event) throws IOException {
        setColorButton(buttonHome);
        anchor.getChildren().setAll(open.initMenu("/coursework/controllers/home.fxml"));
    }

    @FXML
    void onClicked_Learn(MouseEvent event) throws IOException {
        setColorButton(buttonLearn);
        anchor.getChildren().setAll(open.initMenu("/coursework/controllers/learn.fxml"));
    }

    @FXML
    void initialize() throws IOException {
        anchor.getChildren().setAll(open.initMenu("/coursework/controllers/home.fxml"));
        Session singleton = Session.getInstance();
        singleton.setAnchorView(anchor);
        singleton.setButtonHome(buttonHome);
        singleton.setButtonLearn(buttonLearn);

        singleton.setBaseAnchor(baseAnchor);
    }
}
