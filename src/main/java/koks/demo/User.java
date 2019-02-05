package koks.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    protected User() {
    }

    public User(String namePerson, String iban, Integer personalFunds) {
        this.namePerson = namePerson;
        this.personalFunds = personalFunds;
        this.iban = iban;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private String namePerson;
    private Integer personalFunds;
    private String iban;

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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", namePerson='" + namePerson + '\'' +
                ", personalFunds=" + personalFunds +
                ", iban='" + iban + '\'' +
                '}';
    }
}
