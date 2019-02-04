package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

import javax.sql.RowSet;
import javax.sql.RowSetListener;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.*;

@Repository
public class UserRepository {
    public UserRepository() {
    }

    private static final String INNER_JOIN = "SELECT namePerson, funds, oibPerson FROM racuni INNER JOIN korisnici ON oibPerson = accOIB;";

    @Autowired
    JdbcTemplate jdbcTemplate;

    Map getAllUsers(){
        User as = new User();
        Map<String,List> auto= new HashMap<>();

        jdbcTemplate.query(INNER_JOIN, (ResultSet rs) -> {
            while (rs.next()){
                as.setPersonalFunds(rs.getInt("funds"));
                as.setOibPerson(rs.getString("oibPerson"));
                auto.put(rs.getString("namePerson"), as.getList());
            }
        });
        return auto;
    }

    Map<String,Integer> getUser(String accOIB, Integer id){
        Map asd = new HashMap<String, Integer>();

        asd.put(accOIB, jdbcTemplate.queryForMap("SELECT * FROM racuni where accOIB = " + accOIB +";",Integer.class));

        return asd;
    }

    //
    List<String> getAllOIBs(){
        return new ArrayList<>(jdbcTemplate.queryForList(
                "SELECT oibPerson FROM korisnici;",String.class));
    }
}
