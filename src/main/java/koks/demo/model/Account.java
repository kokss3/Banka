package koks.demo.model;

public class Account {

    public Account(){
        super();
    }

    public Account(Integer user_id, String realName, String iban, double funds) {
        this.user_id = user_id;
        this.realName = realName;
        this.iban = iban;
        this.funds = funds;
    }

    private Integer id;
    private Integer user_id;
    private String realName;
    private String iban;
    private double funds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
                "user_id=" + user_id +
                ", realName='" + realName + '\'' +
                ", iban='" + iban + '\'' +
                ", funds=" + funds +
                '}';
    }
}
