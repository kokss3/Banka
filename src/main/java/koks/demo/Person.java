package koks.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String person;
    private int accountId;
    private double personalFunds;

    public Person(){

    }

    public Person(String person, int accountId, double personalFunds) {
        this.person = person;
        this.accountId = accountId;
        this.personalFunds = personalFunds;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getPersonalFunds() {
        return personalFunds;
    }

    public void setPersonalFunds(double funds) {
        this.personalFunds = personalFunds;
    }

    public long getId() {
        return id;
    }
}
