package coursework.models;

import java.io.Serializable;

public class Servqual implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Archive archive;

    private User user;

    private Integer politeness;

    private Integer equipment;

    private Integer timetable;

    private Integer products;

    private Integer access;

    public Servqual(Archive archive, User user, Integer politeness, Integer equipment,
                    Integer timetable, Integer products, Integer access) {
        this.archive = archive;
        this.user = user;
        this.politeness = politeness;
        this.equipment = equipment;
        this.timetable = timetable;
        this.products = products;
        this.access = access;
    }

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