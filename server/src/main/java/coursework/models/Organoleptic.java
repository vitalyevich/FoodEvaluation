package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "organoleptics")
@Entity
public class Organoleptic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "archive_id", nullable = false)
    private Archive archive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "visual", nullable = false)
    private Integer visual;

    @Column(name = "tactile", nullable = false)
    private Integer tactile;

    @Column(name = "olfactory", nullable = false)
    private Integer olfactory;

    @Column(name = "gustatory", nullable = false)
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