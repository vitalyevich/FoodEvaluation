package coursework;

import coursework.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Access.class);
                configuration.addAnnotatedClass(Archive.class);
                configuration.addAnnotatedClass(Servqual.class);
                configuration.addAnnotatedClass(Measuring.class);
                configuration.addAnnotatedClass(Organoleptic.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(ProductType.class);
                configuration.addAnnotatedClass(Product.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
