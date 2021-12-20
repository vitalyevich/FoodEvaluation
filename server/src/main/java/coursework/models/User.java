package coursework.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private  static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "login_name", nullable = false, length = 20)
    private String loginName;

    @Column(name = "age_num", nullable = false)
    private Integer ageNum;

    @Column(name = "gender", nullable = false)
    private Character gender;

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getAgeNum() {
        return ageNum;
    }

    public void setAgeNum(Integer ageNum) {
        this.ageNum = ageNum;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}