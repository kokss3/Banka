package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    /**
     * Za asd se poigrao samo sa implementacijom i vrtnjom logike
     *
     * */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> findAll(){
        return jdbcTemplate.query("select * from korisnici", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNamePerson(rs.getString("namePerson"));
            user.setIban(rs.getString("IBAN"));
            user.setPersonalFunds(rs.getInt("funds"));
            return user;
        });
    }

    public List<User> showSpecific(String iban){
        List<User> tempList = new ArrayList<>();
        for(User temp:findAll()){
            if(iban.equals(temp.getIban())) {
                tempList.add(temp);
            }
        }
        return tempList;
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
