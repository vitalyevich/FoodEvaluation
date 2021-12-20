package coursework.services;

import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.ProductDAO;
import coursework.models.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private List<Product> productsList;
    @Override
    public void save(Product product) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("from Product where productName =:product");
        query.setParameter("product", product.getProductName());

        productsList = query.list();

        if(productsList.isEmpty()) {
            session.save(product);
        }
        else {
            throw new SQLException();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product products) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Product products1 = (Product) session.get(Product.class, products.getId());
        products1.setProductName(products.getProductName());
        products1.setPrice(products.getPrice());
        products1.setMenuDescription(products.getMenuDescription());
        products1.setType(products.getType());

        session.update(products1);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Product products = (Product) session.get(Product.class, id);
        session.delete(products);

        transaction.commit();
        session.close();
    }

    @Override
    public List<Product> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        productsList = session.createQuery("from Product ").list();

        transaction.commit();
        session.close();

        return productsList;
    }
}
