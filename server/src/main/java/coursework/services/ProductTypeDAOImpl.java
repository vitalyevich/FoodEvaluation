package coursework.services;

import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.ProductDAO;
import coursework.interfaces.ProductTypeDAO;
import coursework.models.Product;
import coursework.models.ProductType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAOImpl implements ProductTypeDAO {

    @Override
    public void save(ProductType product) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        Query query = session.createQuery("from ProductType where productName =:product");
        query.setParameter("product", product.getProductName());

        productTypeList = query.list();

        if(productTypeList.isEmpty()) {
            session.save(product);
        }
        else {
            throw new SQLException();
        }
        transaction.commit();
        session.close();
    }

    @Override
    public void update(ProductType product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        ProductType productType = (ProductType) session.get(ProductType.class, product.getId());
        productType.setProductName(product.getProductName());

        session.update(productType);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        ProductType product = (ProductType) session.get(ProductType.class, id);
        session.delete(product);

        transaction.commit();
        session.close();
    }

    private List<ProductType> productTypeList = new ArrayList<>();

    @Override
    public List<ProductType> view() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        productTypeList = session.createQuery("from ProductType ").list();

        transaction.commit();
        session.close();

        return productTypeList;
    }
}
