package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "measurings")
@Entity
public class Measuring implements Serializable {

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

    @Column(name = "weight_status", nullable = false)
    private Integer weightStatus;

    @Column(name = "ingridient_status", nullable = false)
    private Integer ingridientStatus;

    @Column(name = "ready_status", nullable = false)
    private Integer readyStatus;

    @Column(name = "structute_status", nullable = false)
    private Integer structuteStatus;

    @Column(name = "quantity_status", nullable = false)
    private Integer quantityStatus;

    public Integer getQuantityStatus() {
        return quantityStatus;
    }

    public void setQuantityStatus(Integer quantityStatus) {
        this.quantityStatus = quantityStatus;
    }

    public Integer getStructuteStatus() {
        return structuteStatus;
    }

    public void setStructuteStatus(Integer structuteStatus) {
        this.structuteStatus = structuteStatus;
    }

    public Integer getReadyStatus() {
        return readyStatus;
    }

    public void setReadyStatus(Integer readyStatus) {
        this.readyStatus = readyStatus;
    }

    public Integer getIngridientStatus() {
        return ingridientStatus;
    }

    public void setIngridientStatus(Integer ingridientStatus) {
        this.ingridientStatus = ingridientStatus;
    }

    public Integer getWeightStatus() {
        return weightStatus;
    }

    public void setWeightStatus(Integer weightStatus) {
        this.weightStatus = weightStatus;
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