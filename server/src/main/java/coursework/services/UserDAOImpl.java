package coursework.services;

import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.UserDAO;
import coursework.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private List<User> usersList = new ArrayList<>();

    @Override
    public int findByUser(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        usersList = session.createQuery("from User where loginName =:login")
                .setParameter("login", login).list();

        transaction.commit();
        session.close();

        return usersList.get(0).getId();
    }

    @Override
    public int save(User user) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("from User where loginName =:login");
        query.setParameter("login", user.getLoginName());


        usersList = query.list();

        if(usersList.isEmpty()) {
            session.save(user);
            query = session.createQuery("from User where loginName =:login");
            usersList = query.setParameter("login", user.getLoginName()).list();
        }
        else {
            throw new SQLException();
        }

        transaction.commit();
        session.close();

        return usersList.get(0).getId();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        User users1 = (User) session.get(User.class, user.getId());
        users1.setLoginName(user.getLoginName());
        users1.setGender(user.getGender());
        users1.setAgeNum(user.getAgeNum());

        session.update(users1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();
    }
}
