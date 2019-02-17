package koks.demo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthUser {

    @Id
    private Integer id;
    private String username;
    private String password;



    public AuthUser(String username, String password) {
        this.username = username;
        this.password = password;
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
}