package coursework.interfaces;

import coursework.models.Product;
import coursework.models.ProductType;

import java.sql.SQLException;
import java.util.List;

public interface ProductTypeDAO {
    public void save(ProductType product) throws SQLException;
    public void update(ProductType product);
    public void delete(int id);
    public List<ProductType> view();
}
