package koks.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

    public Account(){
        super();
    }

    public Account(String iban, double funds) {
        this.iban = iban;
        this.funds = funds;
    }

    @Id
    private int id;
    private String realName;
    private String iban;
    private double funds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
}
