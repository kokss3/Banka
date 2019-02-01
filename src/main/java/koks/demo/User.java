package koks.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String namePerson;
    private Integer personalFunds;
    private String oibPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
