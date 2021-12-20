package coursework.rmi;

import coursework.models.Product;
import coursework.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillingClientTest {

    private BillingClient client;

    @BeforeEach
    void setClient() {
        client = new BillingClient();
    }

    @Test
    void findByUser() throws RemoteException {
        int actual = 1;
        int expected = client.findByUser("Максим");
        Assert.assertEquals(actual,expected);
    }

    @Test
    void addNewUser_CONNECTION() {
        try {
            client.addNewUser(new User());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            Assert.fail("Соединение с базой данных не установлено!");
        }
    }

    @Test
    void addNewUser_EXCEPTION() {
        try {
            client.addNewUser(new User("Максим",18,'М'));
        } catch (SQLException e) {
            Assert.fail("Данное имя занято!");
        } catch (RemoteException e) { }
    }

    @Test
    void getListProduct() throws RemoteException {
        List<Product> products = client.getListProduct();
        Assert.assertNotNull(products);
    }

    @Test
    void getListProduct_CHECK() throws RemoteException {
        List<Product> products = client.getListProduct();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getProductName().equals("Сосиска в тесте")) {
                Assert.assertTrue(products.get(i).getProductName().equals("Сосиска в тесте"));
            }
        }
    }
}