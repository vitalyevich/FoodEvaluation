package coursework.services;

import coursework.models.Access;
import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.AccessDAO;
import coursework.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class AccessDAOImpl implements AccessDAO {

    private List<Access> accesses = new ArrayList<>();
    @Override
    public int findByAccess(String login, String password) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        accesses = session.createQuery("from Access where user.loginName =:login")
                .setParameter("login", login).list();

        if (!accesses.isEmpty()) {
            if(BCrypt.checkpw(password, accesses.get(0).getPassword())) {
                if (accesses.get(0).getAccessStatus() != -1 && accesses.get(0).getAccessStatus() != 0) {
                    return accesses.get(0).getRole().getId();
                }
                else {
                    return -1;
                }
            }
        }
        transaction.commit();
        session.close();

        return 0;
    }

    @Override
    public void save(Access access) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(access);

        transaction.commit();
        session.close();
    }

    @Override
    public void update(Access access) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Access access1 = (Access) session.get(Access.class, access.getId());
        access1.setAccessStatus(access.getAccessStatus());
        access1.setPassword(access.getPassword());
        access1.setRole(access.getRole());
        session.update(access1);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Access> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        accesses = session.createQuery("from Access").list();

        transaction.commit();
        session.close();

        return accesses;
    }

    @Override
    public List<Access> search(int searchId) {
        return null;
    }
}
