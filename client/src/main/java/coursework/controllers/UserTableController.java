package coursework.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import coursework.interfaces.Open;
import coursework.models.Access;
import coursework.operations.Notification;
import coursework.operations.OperationWithUserImpl;
import coursework.operations.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UserTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Access> tableUser;

    @FXML
    private TableColumn<Access, Integer> numberColumn;

    @FXML
    private TableColumn<Access, String> loginColumn;

    @FXML
    private TableColumn<Access, String> roleColumn;

    @FXML
    private TableColumn<Access, String> statusColumn;

    @FXML
    private TableColumn<Access, Integer> ageColumn;

    @FXML
    private Button buttonEdit;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonDel;

    @FXML
    private ComboBox<String> type;

    @FXML
    private TextField search;

    private Open open = new Open();

    @FXML
    void onClicked_Add(MouseEvent event) {
        open.initFXML(buttonAdd,"/coursework/controllers/addUser.fxml","Добавление пользователя",0);
    }

    @FXML
    void onClicked_Save(MouseEvent event) {
        notification.showAlertWithoutHeaderText("Запись в файл", "Успешное добавление в файл!");
        try(FileWriter writer = new FileWriter("user.txt", false))
        {
            for (int i = 0; i < accesses.size(); i++) {
                writer.write(accesses.get(i).getId() + " " + accesses.get(i).getRole().getRoleName() + " " + accesses.get(i).getLogin() + " "
                + accesses.get(i).getStatus() + " " + accesses.get(i).getAge());
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException e){ }
    }

    private Notification notification = new Notification();
    @FXML
    void onClicked_Del(MouseEvent event) throws RemoteException {
        boolean check = notification.showConfirmation("Удаление пользователя", "Вы действительно желаете " +
                "удалить данного пользователя?", "Для того, чтобы продолжить, нажмите кнопку!");
        if (check) {
            operationWithUser.deleteUser(tableUser.getSelectionModel().getSelectedItem().getId());
            tableUser.getItems().remove(tableUser.getSelectionModel().getSelectedItem());
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    @FXML
    void onClicked_Table(MouseEvent event) {
        if (tableUser.getSelectionModel().getSelectedItem() != null && tableUser.getSelectionModel().getSelectedItem().getUser().getId() != 1) {
            buttonDel.setDisable(false);
            buttonEdit.setDisable(false);
        } else {
            buttonEdit.setDisable(true);
            buttonDel.setDisable(true);
        }
    }

    @FXML
    void onClicked_Edit(MouseEvent event) {
        session.setAccess(tableUser.getSelectionModel().getSelectedItem());
        open.initFXML(buttonEdit,"/coursework/controllers/editUser.fxml","Редактирование пользователя",0);
    }

    private OperationWithUserImpl operationWithUser = new OperationWithUserImpl();
    private Session session = Session.getInstance();
    @FXML
    void initialize() throws RemoteException {
        accesses = FXCollections.observableArrayList(operationWithUser.viewUsers());
        session.setUserTotal(accesses.get(accesses.size() - 1).getId());
        fillingTable(accesses);

        type.getItems().add("");
        type.getItems().add("Логин");
        type.getItems().add("Роль");
        type.getItems().add("Статус");
        type.getItems().add("Возраст");
        filtrationUser();
    }

    @FXML
    void onAction_Type(ActionEvent event) {
        if (type.getValue().equals("") || type.getValue().equals("Фильтрация")){
            fillingTable(accesses);
            search.setDisable(true);
        }
        else {
            search.setDisable(false);
        }
    }

    private void filtrationUser() {
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {

                tempAccess.clear();
                tempAccess = FXCollections.observableArrayList();

                if (type.getValue().equals("Логин")) {
                    for (int i = 0; i < accesses.size(); i++) {
                        if (accesses.get(i).getUser().getLoginName().toLowerCase().contains(newValue.toLowerCase().trim())) {
                            tempAccess.add(accesses.get(i));
                        }
                    }
                } else if (type.getValue().equals("Роль")) {
                    for (int i = 0; i < accesses.size(); i++) {
                        if (accesses.get(i).getRoleUser().contains(newValue.toLowerCase().trim())) {
                            tempAccess.add(accesses.get(i));
                        }
                    }
                } else if (type.getValue().equals("Статус")) {
                    for (int i = 0; i < accesses.size(); i++) {
                        if (accesses.get(i).getStatus().contains(newValue.toLowerCase().trim())) {
                            tempAccess.add(accesses.get(i));
                        }
                    }
                } else if (type.getValue().equals("Возраст")) {
                    for (int i = 0; i < accesses.size(); i++) {
                        try {
                            if (accesses.get(i).getUser().getAgeNum() == Integer.parseInt(newValue.toLowerCase().trim())) {
                                tempAccess.add(accesses.get(i));
                            }
                        } catch (Exception e) { }
                    }
                }
                else {
                    fillingTable(accesses);
                }
                fillingTable(tempAccess);
            } catch (NullPointerException e) { fillingTable(accesses); }
        });
    }

    private ObservableList<Access> accesses = FXCollections.observableArrayList();
    private ObservableList<Access> tempAccess = FXCollections.observableArrayList();

    private void fillingTable(ObservableList<Access> accesses) {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("roleUser"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableUser.setItems(accesses);
    }
}
