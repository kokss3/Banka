package koks.demo.model;

public class Account {

    public Account(){
        super();
    }

    public Account(Integer id, String realName, String iban, double funds) {
        this.id = id;
        this.realName = realName;
        this.iban = iban;
        this.funds = funds;
    }

    private Integer id;
    private String realName;
    private String iban;
    private double funds;

    public Integer getId() {
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

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", iban='" + iban + '\'' +
                ", funds=" + funds +
                '}';
    }
}
