package coursework.services;

import coursework.HibernateSessionFactoryUtil;
import coursework.interfaces.ArchiveDAO;
import coursework.models.Archive;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.management.Query;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchiveDAOImpl implements ArchiveDAO {

    private List<Archive> archiveList = new ArrayList<>();
    @Override
    public int save(Archive archive) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(archive);
        archive.setArchiveDate(LocalDate.parse(archive.getArchiveDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        archive.setArchiveTime(LocalTime.parse(archive.getArchiveTime().format(DateTimeFormatter.ofPattern("hh:mm"))));
        transaction.commit();

        session.close();

        return archive.getId();
    }
}
