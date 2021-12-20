package coursework.models;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private ProductType type;

    private String productName;

    private String menuDescription;

    private Long price;

    public Product() { }

    public Product(Integer id, ProductType type, String productName, String menuDescription, Long price) {
        this.id = id;
        this.type = type;
        this.productName = productName;
        this.menuDescription = menuDescription;
        this.price = price;
    }

    public Product(ProductType type, String productName, String menuDescription, Long price) {
        this.type = type;
        this.productName = productName;
        this.menuDescription = menuDescription;
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeProduct() {
        return type.getProductName();
    }
}