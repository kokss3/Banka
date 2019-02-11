package koks.demo.Model;


import javax.persistence.*;

@Entity
@Table
public class User {

    public User() {
        super();
    }

    public User( String name, String iban, Integer funds) {
        super();
        this.name = name;
        this.funds = funds;
        this.iban = iban;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer funds;
    private String iban;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
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
                ", name='" + name + '\'' +
                ", funds=" + funds +
                ", iban='" + iban + '\'' +
                '}';
    }
}
