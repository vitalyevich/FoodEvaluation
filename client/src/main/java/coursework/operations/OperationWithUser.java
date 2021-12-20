package coursework.operations;

import coursework.models.Access;
import coursework.models.User;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface OperationWithUser {

    public void addNewUser(User user, Access access) throws SQLException, RemoteException;
    public List<Access> viewUsers() throws RemoteException;
    public int findAccess(String login, String password) throws RemoteException;
    public int findUser(String login) throws RemoteException;
    public void deleteUser(int id) throws RemoteException;
    public void editUser(User user, Access access) throws RemoteException;
}
