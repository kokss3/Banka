package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.RowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    /**
     * Za asd se poigrao samo sa queryjima
     *
     * */
    @Autowired
    JdbcTemplate jdbcTemplate;

    private User doSomething(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setNamePerson(rs.getString("namePerson"));
        user.setIban(rs.getString("IBAN"));
        user.setPersonalFunds(rs.getInt("funds"));
        return user;
    }


    public List<User> findAll(){
        String query = "select * from korisnici";
        return jdbcTemplate.query(query, (rs,rowNum) -> doSomething(rs));
    }

    public List<User> findAll(String message){
        String query = message;
        return jdbcTemplate.query(query, (rs,rowNum) -> doSomething(rs));
    }


    public List<User> showSpecific(String iban){
        String query = "select * from korisnici where iban="+iban+";";
        return findAll(query);
    }

    public User sumAllFunds(String iban){
        User user = new User();
        user.setId(showSpecific(iban).get(0).getId());
        user.setNamePerson(showSpecific(iban).get(0).getNamePerson());
        user.setIban(showSpecific(iban).get(0).getIban());
        Integer sum=0;
        for (User temp: showSpecific(iban)) {
            sum += temp.getPersonalFunds();
        }
        user.setPersonalFunds(sum);

        return user;
    }
}
