package coursework.controllers;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import coursework.models.*;
import coursework.operations.OperationWithTestImpl;
import coursework.rmi.BillingClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

public class StatsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<String, Number> Chart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private ComboBox<String> test;

    @FXML
    private ComboBox<String> type;

    @FXML
    private ComboBox<String> product;

    @FXML
    void onAction_Type(ActionEvent event) throws RemoteException {
        product.getItems().clear();
        products = client.getListProduct();
        for (int i = 0; i < products.size(); i++) {
            product.getItems().add(products.get(i).getProductName());
        }
    }

    private OperationWithTestImpl operationWithTest = new OperationWithTestImpl();
    @FXML
    void onAction_Product(ActionEvent event) throws RemoteException {

        Chart.getData().removeAll(Chart.getData());
        XYChart.Series series = new XYChart.Series();
        series.setName("Оцениваемая продукция");

        if (test.getValue().equals("Органолептический")) {
            organoleptics = operationWithTest.viewTestOrganoleptic(product.getValue());
            series.getData().add(new XYChart.Data<>("Внешний вид", organoleptics.get(0).getVisual()));
            series.getData().add(new XYChart.Data<>("Осязание", organoleptics.get(0).getTactile()));
            series.getData().add(new XYChart.Data<>("Обоняние", organoleptics.get(0).getOlfactory()));
            series.getData().add(new XYChart.Data<>("Вкус", organoleptics.get(0).getGustatory()));
        } else if (test.getValue().equals("Оценка качества сервиса")) {
            servquals = operationWithTest.viewTestServqual(product.getValue());
            series.getData().add(new XYChart.Data<>("Вежливость", servquals.get(0).getPoliteness()));
            series.getData().add(new XYChart.Data<>("Оборудование", servquals.get(0).getEquipment()));
            series.getData().add(new XYChart.Data<>("Время", servquals.get(0).getTimetable()));
            series.getData().add(new XYChart.Data<>("Товары", servquals.get(0).getProducts()));
            series.getData().add(new XYChart.Data<>("Доступ", servquals.get(0).getAccess()));
        } else {
            measurings = operationWithTest.viewTestMeasuring(product.getValue());
            series.getData().add(new XYChart.Data<>("Вес", measurings.get(0).getWeightStatus()));
            series.getData().add(new XYChart.Data<>("Ингридиенты", measurings.get(0).getIngridientStatus()));
            series.getData().add(new XYChart.Data<>("Готовность", measurings.get(0).getReadyStatus()));
            series.getData().add(new XYChart.Data<>("Состав", measurings.get(0).getStructuteStatus()));
            series.getData().add(new XYChart.Data<>("Количество", measurings.get(0).getQuantityStatus()));
        }
        Chart.getData().addAll(series);
    }

    @FXML
    void onAction_Test(ActionEvent event) throws RemoteException {
        type.getItems().clear();

        productTypeList = client.getListTypeProduct();
        for (int i = 0; i < productTypeList.size(); i++) {
            type.getItems().add(productTypeList.get(i).getProductName());
        }
    }


    private List<Measuring> measurings = new ArrayList();
    private List<Organoleptic> organoleptics = new ArrayList();
    private List<Servqual> servquals = new ArrayList();

    private List<ProductType> productTypeList = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private BillingClient client = new BillingClient();
    @FXML
    void initialize() throws RemoteException {

        test.getItems().add("Органолептический");
        test.getItems().add("Оценка качества сервиса");
        test.getItems().add("Измерительный");
    }
}
