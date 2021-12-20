package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import coursework.models.*;
import coursework.operations.Notification;
import coursework.operations.OperationWithTestImpl;
import coursework.operations.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class TestServqualController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> politeness;

    @FXML
    private ComboBox<Integer> equipment;

    @FXML
    private ComboBox<Integer> timetable;

    @FXML
    private ComboBox<Integer> products;

    @FXML
    private ComboBox<Integer> access;

    @FXML
    private Button buttonSave;

    private Notification notification = new Notification();
    private OperationWithTestImpl operationWithTest = new OperationWithTestImpl();
    private Session session = Session.getInstance();

    @FXML
    void onClicked_Save(MouseEvent event) {
        try {
            if (!politeness.getValue().equals("") && !equipment.getValue().equals("") && !timetable.getValue().equals("") &&
                    !products.getValue().equals("") && !access.getValue().equals("")) {

                boolean check = notification.showConfirmation("Тест", "Вы подтверждаете сохранение теста?","Пожалуйста, нажмите кнопку!");

                if (check) {
                    Archive archive = new Archive();
                    archive.setArchiveDate(LocalDate.now());
                    archive.setArchiveTime(LocalTime.now());

                    User user = new User();
                    user.setId(session.getUserId());


                    operationWithTest.addTestServqual(new Servqual(archive, user,
                            politeness.getValue(), equipment.getValue(), timetable.getValue(),
                            products.getValue(),access.getValue()));

                    notification.showAlertWithoutHeaderText("Тест", "Успешное добавление результатов!");
                }
            }
            else {
                notification.showAlertWithoutWarning("Тест", "Вы не заполнили все данные, пожалуйста, проверьте еще раз!");
            }
        } catch (NullPointerException e) {
            notification.showAlertWithoutWarning("Тест", "Вы не заполнили все данные, пожалуйста, проверьте еще раз!");
        } catch (RemoteException e) {
            notification.showAlertWithoutWarning("Тест", "Соединение с сервером потеряно!");
        }
    }

    @FXML
    void initialize() {
        for (int i = 1; i <= 10; i++) {
            politeness.getItems().add(i);
            equipment.getItems().add(i);
            timetable.getItems().add(i);
            products.getItems().add(i);
            access.getItems().add(i);
        }
    }
}
