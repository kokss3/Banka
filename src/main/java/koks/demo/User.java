package koks.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Entity
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private Integer accId;
    private String namePerson;
    private Integer personalFunds;
    private String oibPerson;

    public void checkIfMore(){

    }

    public List getList(){
        List<String> a = new ArrayList<>();

      //  a.add(namePerson);
        a.add(personalFunds.toString());
        a.add(oibPerson);
        return a;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public Integer getPersonalFunds() {
        return personalFunds;
    }

    public void setPersonalFunds(Integer personalFunds) {
        this.personalFunds = personalFunds;
    }

    public String getOibPerson() {
        return oibPerson;
    }

    public void setOibPerson(String oibPerson) {
        this.oibPerson = oibPerson;
    }


}
