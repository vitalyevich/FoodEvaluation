package coursework.interfaces;

import coursework.models.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    public void save(Product product) throws SQLException;
    public void update(Product product);
    public void delete(int id);
    public List<Product> view();
}
