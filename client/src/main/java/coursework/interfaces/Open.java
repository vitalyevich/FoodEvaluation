package coursework.interfaces;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Open implements Openable {


    private AnchorPane anchorPane;

    @Override
    public AnchorPane initMenu(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(path));
        anchorPane = fxmlLoader.load();
        return anchorPane;
    }

    @Override
    public void initFXML(Button buttonClick, String path, String name, int clOp) {
        if(clOp == 1) {
            buttonClick.getScene().getWindow().hide();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle(name);

        stage.show();
    }
}
