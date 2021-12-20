package coursework.operations;

import coursework.models.*;

import java.rmi.RemoteException;
import java.util.List;

public interface OperationWithTest {

    public void addTestOrganoleptic(Organoleptic organoleptic) throws RuntimeException, RemoteException;
    public void addTestMeasuring(Measuring measuring) throws RuntimeException, RemoteException;
    public void addTestServqual(Servqual servqual) throws RuntimeException, RemoteException;

    public List<Organoleptic> viewTestOrganoleptic(String productName) throws RuntimeException, RemoteException;
    public List<Measuring> viewTestMeasuring(String productName) throws RuntimeException, RemoteException;
    public List<Servqual> viewTestServqual(String productName) throws RuntimeException, RemoteException;
}
