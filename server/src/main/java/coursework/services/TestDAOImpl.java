package coursework.services;

import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.TestDAO;
import coursework.models.Measuring;
import coursework.models.Organoleptic;
import coursework.models.Servqual;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDAOImpl implements TestDAO {

    @Override
    public void saveMeasuringTest(Measuring measurings) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(measurings);

        transaction.commit();
        session.close();
    }

    @Override
    public void saveOrganolepticTest(Organoleptic organoleptics) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(organoleptics);

        transaction.commit();
        session.close();
    }

    @Override
    public void saveServqualTest(Servqual servquals) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(servquals);

        transaction.commit();
        session.close();
    }

    private List<Measuring> measurings = new ArrayList<>();
    private List<Organoleptic> organoleptics = new ArrayList<>();
    private List<Servqual> servquals = new ArrayList<>();

    @Override
    public List<Measuring> viewMeasuringTest(String productName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        measurings = session.createQuery("FROM Measuring").list();

        transaction.commit();
        session.close();

        return measurings;
    }

    @Override
    public List<Organoleptic> viewOrganolepticTest(String productName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        organoleptics = session.createQuery("FROM Organoleptic").list();
        transaction.commit();
        session.close();

        return organoleptics;
    }

    @Override
    public List<Servqual> viewServqualTest(String productName) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        servquals = session.createQuery("FROM Servqual").list();

        transaction.commit();
        session.close();

        return servquals;
    }
}
