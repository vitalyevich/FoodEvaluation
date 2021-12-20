package coursework.interfaces;

import coursework.models.Access;

import java.util.List;

public interface AccessDAO {

    public int findByAccess(String login, String password);
    public void save(Access access);
    public void update(Access access);
    public List<Access> view();
    public List<Access> search(int searchId);
}
