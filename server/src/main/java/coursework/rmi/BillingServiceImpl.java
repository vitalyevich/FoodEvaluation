package coursework.rmi;

import coursework.interfaces.ProductDAO;
import coursework.interfaces.ProductTypeDAO;
import coursework.interfaces.TestDAO;
import coursework.models.*;
import coursework.services.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {

    private final AccessDAOImpl accessDAO;
    private final UserDAOImpl userDAO;
    private final ProductDAOImpl productDAO;
    private final TestDAOImpl testDAO;
    private final ProductTypeDAOImpl productTypeDAO;
    private final ArchiveDAOImpl archiveDAO;

    public BillingServiceImpl() throws RemoteException {

        this.accessDAO = new AccessDAOImpl();
        this.userDAO = new UserDAOImpl();
        this.productDAO = new ProductDAOImpl();
        this.testDAO = new TestDAOImpl();
        this.productTypeDAO = new ProductTypeDAOImpl();
        this.archiveDAO = new ArchiveDAOImpl();
    }

    public static void main(String[] args) {

        String localhost = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";

        try {
            System.setProperty(RMI_HOSTNAME, localhost);

            String serviceName = "BillingService";
            System.out.println("Инициализация " + serviceName);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(serviceName, new BillingServiceImpl());

            System.out.println("Старт " + serviceName);

        } catch (RemoteException e) {
            System.err.println("RemoteException : " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
    }

    @Override
    public int findByAccess(String login, String password) throws RemoteException {
        return accessDAO.findByAccess(login, password);
    }

    @Override
    public int findByUser(String login) throws RemoteException {
        return userDAO.findByUser(login);
    }

    @Override
    public List<Access> getListAccess() throws RemoteException {
        return accessDAO.view();
    }

    @Override
    public void addNewAccess(Access access) throws RemoteException {
        accessDAO.save(access);
    }

    @Override
    public void editAccess(Access access) throws RemoteException {
        accessDAO.update(access);
    }

    @Override
    public int addNewUser(User user) throws SQLException {
        return userDAO.save(user);
    }

    @Override
    public void editUser(User user) {
        userDAO.update(user);
    }

    @Override
    public void delUser(int id) {
        userDAO.delete(id);
    }

    @Override
    public void addNewProduct(Product product) throws SQLException, RemoteException {
        productDAO.save(product);
    }

    @Override
    public void editProduct(Product product) throws RemoteException {
        productDAO.update(product);
    }

    @Override
    public void delProduct(int id) throws RemoteException {
        productDAO.delete(id);
    }

    @Override
    public List<Product> getListProduct() throws RemoteException {
        return productDAO.view();
    }

    @Override
    public void addNewTypeProduct(ProductType product) throws SQLException, RemoteException {
        productTypeDAO.save(product);
    }

    @Override
    public void editTypeProduct(ProductType product) throws RemoteException {
        productTypeDAO.update(product);
    }

    @Override
    public void delTypeProduct(int id) throws RemoteException {
        productTypeDAO.delete(id);
    }

    @Override
    public List<ProductType> getListTypeProduct() throws RemoteException {
        return productTypeDAO.view();
    }

    @Override
    public void addNewMeasuringTest(Measuring measuring) throws RemoteException {
        testDAO.saveMeasuringTest(measuring);
    }

    @Override
    public void addNewServqualTest(Servqual servqual) throws RemoteException {
        testDAO.saveServqualTest(servqual);
    }

    @Override
    public void addNewOrganolepticTest(Organoleptic organoleptic) throws RemoteException {
        testDAO.saveOrganolepticTest(organoleptic);
    }

    @Override
    public List<Measuring> viewMeasuringTest(String productName) throws RemoteException {
        return testDAO.viewMeasuringTest(productName);
    }

    @Override
    public List<Servqual> viewServqualTest(String productName) throws RemoteException {
        return testDAO.viewServqualTest(productName);
    }

    @Override
    public List<Organoleptic> viewOrganolepticTest(String productName) throws RemoteException {
        return testDAO.viewOrganolepticTest(productName);
    }

    @Override
    public int addNewArchive(Archive archive) throws RemoteException {
        return archiveDAO.save(archive);
    }

}
