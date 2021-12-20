package coursework.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Archive implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private LocalDate archiveDate;

    private LocalTime archiveTime;

    public LocalTime getArchiveTime() {
        return archiveTime;
    }

    public void setArchiveTime(LocalTime archiveTime) {
        this.archiveTime = archiveTime;
    }

    public LocalDate getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(LocalDate archiveDate) {
        this.archiveDate = archiveDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}