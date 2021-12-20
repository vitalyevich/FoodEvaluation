package coursework.interfaces;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public interface Openable {

    public AnchorPane initMenu(String path) throws IOException;
    public void initFXML(Button buttonClick, String path, String name, int clOp);
}
