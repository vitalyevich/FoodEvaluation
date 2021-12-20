package coursework.rmi;

import coursework.models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface BillingService extends Remote {

    public int findByAccess(String login, String password) throws RemoteException;
    public int findByUser(String login) throws RemoteException;
    public List<Access> getListAccess() throws RemoteException;
    public void addNewAccess(Access access) throws RemoteException;
    public void editAccess(Access access) throws RemoteException;

    public int addNewUser(User user) throws SQLException, RemoteException;
    public void editUser(User user) throws RemoteException;
    public void delUser(int id) throws RemoteException;

    public void addNewProduct(Product product) throws SQLException, RemoteException;
    public void editProduct(Product product) throws RemoteException;
    public void delProduct(int id) throws RemoteException;
    public List<Product> getListProduct() throws RemoteException;

    public void addNewTypeProduct(ProductType product) throws SQLException, RemoteException;
    public void editTypeProduct(ProductType product) throws RemoteException;
    public void delTypeProduct(int id) throws RemoteException;
    public List<ProductType> getListTypeProduct() throws RemoteException;

    public void addNewMeasuringTest(Measuring measuring) throws RemoteException;
    public void addNewServqualTest(Servqual servqual) throws RemoteException;
    public void addNewOrganolepticTest(Organoleptic organoleptic) throws RemoteException;

    public List<Measuring> viewMeasuringTest(String productName) throws RemoteException;
    public List<Servqual> viewServqualTest(String productName) throws RemoteException;
    public List<Organoleptic> viewOrganolepticTest(String productName) throws RemoteException;

    public int addNewArchive(Archive archive) throws RemoteException;
}
