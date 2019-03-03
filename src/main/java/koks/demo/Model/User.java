package koks.demo.Model;

import java.util.List;

public class User {

    public User(){
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

    public User(Integer id, List<Account> accounts, List<String> roles) {
        this.id = id;
        this.accounts = accounts;
        this.roles = roles;
    }

    private Integer id;
    private String username;
    private String password;

    private List<Account> accounts;
    private List<String> roles;

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounts=" + accounts +
                ", roles=" + roles +
                '}';
    }
}
