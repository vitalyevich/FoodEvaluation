package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "servquals")
@Entity
public class Servqual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "archive_id", nullable = false)
    private Archive archive;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "politeness", nullable = false)
    private Integer politeness;

    @Column(name = "equipment", nullable = false)
    private Integer equipment;

    @Column(name = "timetable", nullable = false)
    private Integer timetable;

    @Column(name = "products", nullable = false)
    private Integer products;

    @Column(name = "access", nullable = false)
    private Integer access;

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public Integer getProducts() {
        return products;
    }

    public void setProducts(Integer products) {
        this.products = products;
    }

    public Integer getTimetable() {
        return timetable;
    }

    public void setTimetable(Integer timetable) {
        this.timetable = timetable;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public Integer getPoliteness() {
        return politeness;
    }

    public void setPoliteness(Integer politeness) {
        this.politeness = politeness;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}