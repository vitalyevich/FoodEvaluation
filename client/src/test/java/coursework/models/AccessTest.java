package coursework.models;

import coursework.rmi.BillingClient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTest {

    private Access access;

    private BillingClient client = new BillingClient();
    @Test
    void getAllGenre_NO_NULL() throws RemoteException {
        List<Access> accesses = client.getListAccess();
        Assert.assertNotNull(accesses);
    }

    @Test
    public void getHowManyGenre() throws RemoteException {
        int expected = client.getListAccess().size();
        int actual = 3;

        Assert.assertEquals(expected, actual);
    }

}