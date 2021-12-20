package coursework.operations;

import coursework.models.*;
import coursework.rmi.BillingClient;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class OperationWithTestImpl implements OperationWithTest {


    private BillingClient client = new BillingClient();

    public OperationWithTestImpl() {
        this.client = new BillingClient();
    }

    @Override
    public void addTestOrganoleptic(Organoleptic organoleptic) throws RuntimeException, RemoteException {
        Archive archive = new Archive();
        archive.setArchiveDate(LocalDate.now());
        archive.setArchiveTime(LocalTime.now());
        int archiveId = client.addNewArchive(archive);
        archive.setId(archiveId);

        organoleptic.setArchive(archive);

        client.addNewOrganolepticTest(organoleptic);
    }

    @Override
    public void addTestMeasuring(Measuring measuring) throws RuntimeException, RemoteException {
        Archive archive = new Archive();
        archive.setArchiveDate(LocalDate.now());
        archive.setArchiveTime(LocalTime.now());
        int archiveId = client.addNewArchive(archive);
        archive.setId(archiveId);

        measuring.setArchive(archive);

        client.addNewMeasuringTest(measuring);
    }

    @Override
    public void addTestServqual(Servqual servqual) throws RuntimeException, RemoteException {
        Archive archive = new Archive();
        archive.setArchiveDate(LocalDate.now());
        archive.setArchiveTime(LocalTime.now());
        int archiveId = client.addNewArchive(archive);
        archive.setId(archiveId);

        servqual.setArchive(archive);

        client.addNewServqualTest(servqual);
    }

    @Override
    public List<Organoleptic> viewTestOrganoleptic(String productName) throws RuntimeException, RemoteException {
        return client.viewOrganolepticTest(productName);
    }

    @Override
    public List<Measuring> viewTestMeasuring(String productName) throws RuntimeException, RemoteException {
        return client.viewMeasuringTest(productName);
    }

    @Override
    public List<Servqual> viewTestServqual(String productName) throws RuntimeException, RemoteException {
        return client.viewServqualTest(productName);
    }
}
