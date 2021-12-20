package coursework.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "archives")
@Entity
public class Archive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "archive_date", nullable = false)
    private LocalDate archiveDate;

    @Column(name = "archive_time", nullable = false)
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