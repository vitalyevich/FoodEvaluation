package coursework.models;

import java.io.Serializable;

public class Organoleptic implements Serializable {

    private static final long serialVersionUID = 1L;

    public Organoleptic(Product product, Archive archive, User user, Integer visual, Integer tactile,
                        Integer olfactory, Integer gustatory) {
        this.product = product;
        this.archive = archive;
        this.user = user;
        this.visual = visual;
        this.tactile = tactile;
        this.olfactory = olfactory;
        this.gustatory = gustatory;
    }

    private Integer id;

    private Product product;

    private Archive archive;

    private User user;

    private Integer visual;

    private Integer tactile;

    private Integer olfactory;

    private Integer gustatory;

    public Integer getGustatory() {
        return gustatory;
    }

    public void setGustatory(Integer gustatory) {
        this.gustatory = gustatory;
    }

    public Integer getOlfactory() {
        return olfactory;
    }

    public void setOlfactory(Integer olfactory) {
        this.olfactory = olfactory;
    }

    public Integer getTactile() {
        return tactile;
    }

    public void setTactile(Integer tactile) {
        this.tactile = tactile;
    }

    public Integer getVisual() {
        return visual;
    }

    public void setVisual(Integer visual) {
        this.visual = visual;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}