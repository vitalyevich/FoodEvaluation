package coursework.operations;

import coursework.models.Access;
import coursework.models.User;
import coursework.rmi.BillingClient;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class OperationWithUserImpl implements OperationWithUser {

    @Override
    public void addNewUser(User user, Access access) throws SQLException, RemoteException {
        int userId = client.addNewUser(user);
        access.setId(userId);
        client.addNewAccess(access);
    }

    @Override
    public List<Access> viewUsers() throws RemoteException {
        return client.getListAccess();
    }

    @Override
    public int findAccess(String login, String password) throws RemoteException {
        return client.findByAccess(login, password);
    }

    @Override
    public int findUser(String login) throws RemoteException {
        return client.findByUser(login);
    }

    @Override
    public void deleteUser(int id) throws RemoteException {
        client.delUser(id);
    }

    private Session session = Session.getInstance();
    @Override
    public void editUser(User user, Access access) throws RemoteException {
        client.editUser(user);
        client.editAccess(access);
    }

    private BillingClient client;

    public OperationWithUserImpl() {
        this.client = new BillingClient();
    }
}
