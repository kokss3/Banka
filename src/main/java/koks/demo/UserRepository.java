package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    public UserRepository() {
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    /*
    Vidi se koji je table otvoren i odakle ga povlačim  sa localhost-a
    postavljen je mySQL preko XAMPP-a

     */
    List<String> getAllUsers(){
        List<String> usernameList = new ArrayList<>();
        usernameList.addAll(jdbcTemplate.queryForList("SELECT id FROM racuni;",String.class));
        return usernameList;
    }

    List<String> getAllAccounts(){
        List<String> usernameList = new ArrayList<>();
        usernameList.addAll(jdbcTemplate.queryForList("SELECT namePerson FROM racuni;",String.class));
        return usernameList;
    }

    //
    List<String> getAllOIBs(){
        List<String> usernameList = new ArrayList<>();
        usernameList.addAll(jdbcTemplate.queryForList("SELECT oibPerson FROM racuni;",String.class));
        return usernameList;
    }
}
