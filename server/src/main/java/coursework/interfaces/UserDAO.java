package coursework.interfaces;

import coursework.models.User;

import java.sql.SQLException;

public interface UserDAO {

    public int findByUser(String login);
    public int save(User user) throws SQLException;
    public void update(User user);
    public void delete(int id);
}
