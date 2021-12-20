package coursework.models;

import java.io.Serializable;

public class Measuring implements Serializable {

    public Measuring(Product product, Archive archive, User user, Integer weightStatus, Integer ingridientStatus,
                     Integer readyStatus, Integer structuteStatus, Integer quantityStatus) {
        this.product = product;
        this.archive = archive;
        this.user = user;
        this.weightStatus = weightStatus;
        this.ingridientStatus = ingridientStatus;
        this.readyStatus = readyStatus;
        this.structuteStatus = structuteStatus;
        this.quantityStatus = quantityStatus;
    }

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Product product;

    private Archive archive;

    private User user;

    private Integer weightStatus;

    private Integer ingridientStatus;

    private Integer readyStatus;

    private Integer structuteStatus;

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