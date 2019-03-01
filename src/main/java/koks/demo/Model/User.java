package koks.demo.Model;

import java.util.List;

public class User {

    public User() {
        super();
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, List<Account> accounts) {
        this.username = username;
        this.accounts = accounts;
    }

    private Integer id;
    private String username;
    private String password;
    private String realName;
    private List<Account> accounts;
    private List<String> role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", accounts=" + accounts +
                ", role=" + role +
                '}';
    }
}
