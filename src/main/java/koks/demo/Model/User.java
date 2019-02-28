package koks.demo.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    public User() {
        super();
    }

    public User(String username, List<Account> accounts) {
        this.username = username;
        this.accounts = accounts;
        setRealName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String realName;
    private List<Account> accounts;
    private List<AuthUser> credentials;

    private void setRealName(){
        this.realName = accounts.get(0).getRealName();
    }

    public String getRealName() {
        return realName;
    }

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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<AuthUser> getCredentials() {
        return credentials;
    }

    public void setCredentials(List<AuthUser> credentials) {
        this.credentials = credentials;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (id == null || obj == null || getClass() != obj.getClass())
            return false;
        User that = (User) obj;
        return id.equals(that.id);
    }
}
