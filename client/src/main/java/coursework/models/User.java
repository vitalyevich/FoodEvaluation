package coursework.models;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String loginName;

    private Integer ageNum;

    private Character gender;

    public User() { }

    public User(Integer id, String loginName, Integer ageNum, Character gender) {
        this.id = id;
        this.loginName = loginName;
        this.ageNum = ageNum;
        this.gender = gender;
    }

    public User(String loginName, Integer ageNum, Character gender) {
        this.loginName = loginName;
        this.ageNum = ageNum;
        this.gender = gender;
    }

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