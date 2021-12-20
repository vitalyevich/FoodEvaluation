package coursework.rmi;;

import coursework.models.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;


public class BillingClient implements BillingService {

    private String localhost    = "127.0.0.1";
    private String RMI_HOSTNAME = "java.rmi.server.hostname";
    private String SERVICE_PATH = "rmi://localhost/BillingService";

    private BillingService bs;

    public BillingClient()
    {
        try {

            System.setProperty(RMI_HOSTNAME, localhost);

            String objectName = SERVICE_PATH;

            bs = (BillingService) Naming.lookup(objectName);


        } catch (MalformedURLException e) { } catch (RemoteException e) { }
        catch (NotBoundException e) { }
    }

    @Override
    public int findByAccess(String login, String password) throws RemoteException {
        return bs.findByAccess(login, password);
    }

    @Override
    public int findByUser(String login) throws RemoteException {
        return bs.findByUser(login);
    }

    @Override
    public List<Access> getListAccess() throws RemoteException {
        return bs.getListAccess();
    }

    @Override
    public void addNewAccess(Access access) throws RemoteException {
        bs.addNewAccess(access);
    }

    @Override
    public void editAccess(Access access) throws RemoteException {
        bs.editAccess(access);
    }

    @Override
    public int addNewUser(User user) throws SQLException, RemoteException {
       return bs.addNewUser(user);
    }

    @Override
    public void editUser(User user) throws RemoteException {
        bs.editUser(user);
    }

    @Override
    public void delUser(int id) throws RemoteException {
        bs.delUser(id);
    }

    @Override
    public void addNewProduct(Product product) throws SQLException, RemoteException {
        bs.addNewProduct(product);
    }

    @Override
    public void editProduct(Product product) throws RemoteException {
        bs.editProduct(product);
    }

    @Override
    public void delProduct(int id) throws RemoteException {
        bs.delProduct(id);
    }

    @Override
    public List<Product> getListProduct() throws RemoteException {
        return bs.getListProduct();
    }

    @Override
    public void addNewTypeProduct(ProductType product) throws SQLException, RemoteException {
        bs.addNewTypeProduct(product);
    }

    @Override
    public void editTypeProduct(ProductType product) throws RemoteException {
        bs.editTypeProduct(product);
    }

    @Override
    public void delTypeProduct(int id) throws RemoteException {
        bs.delTypeProduct(id);
    }

    @Override
    public List<ProductType> getListTypeProduct() throws RemoteException {
        return bs.getListTypeProduct();
    }

    @Override
    public void addNewMeasuringTest(Measuring measuring) throws RemoteException {
        bs.addNewMeasuringTest(measuring);
    }

    @Override
    public void addNewServqualTest(Servqual servqual) throws RemoteException {
        bs.addNewServqualTest(servqual);
    }

    @Override
    public void addNewOrganolepticTest(Organoleptic organoleptic) throws RemoteException {
        bs.addNewOrganolepticTest(organoleptic);
    }

    @Override
    public List<Measuring> viewMeasuringTest(String productName) throws RemoteException {
        return bs.viewMeasuringTest(productName);
    }

    @Override
    public List<Servqual> viewServqualTest(String productName) throws RemoteException {
        return bs.viewServqualTest(productName);
    }

    @Override
    public List<Organoleptic> viewOrganolepticTest(String productName) throws RemoteException {
        return bs.viewOrganolepticTest(productName);
    }

    @Override
    public int addNewArchive(Archive archive) throws RemoteException {
        return bs.addNewArchive(archive);
    }
}
