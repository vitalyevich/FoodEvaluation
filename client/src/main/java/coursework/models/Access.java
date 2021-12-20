package coursework.models;

import java.io.Serializable;

public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    public Access(int id, Role role, String password, Integer accessStatus) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.accessStatus = accessStatus;
    }

    private Integer id;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    private Role role;

    private String password;

    private Integer accessStatus;

    public Integer getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(Integer accessStatus) {
        this.accessStatus = accessStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return user.getLoginName();
    }

    public String getRoleUser() {
        return role.getRoleName();
    }

    public Integer getAge() {
        return user.getAgeNum();
    }

    public String getStatus() {
        if (accessStatus == -1) {
            return "Заблокирован";
        }
        else if(accessStatus == 0) {
            return "Не подтвержден";
        }
        else {
            return "Подтвержден";
        }
    }
}