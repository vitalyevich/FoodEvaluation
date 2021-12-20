package coursework.operations;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class Notification implements Notifiable {

    private Label label;

    @Override
    public boolean showConfirmation(String title, String head, String content) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);

        ButtonType ok = new ButtonType("Да");
        ButtonType cancel = new ButtonType("Отмена");

        alert.getButtonTypes().clear();

        alert.getButtonTypes().addAll(ok, cancel);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == ok) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showAlertWithoutHeaderText(String title, String head) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);

        alert.setHeaderText(null);
        alert.setContentText(head);

        alert.showAndWait();
    }

    @Override
    public void showAlertWithoutWarning(String title, String head) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);

        alert.setHeaderText(null);
        alert.setContentText(head);

        alert.showAndWait();
    }
}
