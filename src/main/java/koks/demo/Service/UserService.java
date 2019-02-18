package koks.demo.Service;

import koks.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
@Transactional
public class UserService {

    @Autowired
    JdbcTemplate template;

    public List<User> getListByName(String name){
        String query = "select * from user where name=?";

        return template.query(query, new Object[]{name},(rs, rowNum) ->
                new User(rs.getInt("id"),rs.getString("name"),
                        rs.getString("iban"),rs.getInt("funds")));
    }

    //removes multiple entries, and saves new entry, or updates old
    public void saveInDB(User user){
        String query = "select * from user where name=? and iban=?";

        String saving;

        List<User> temp = template.query(query, new Object[]{user.getName(),user.getIban()},(rs, rowNum) ->
                new User(rs.getInt("id"),rs.getString("name"),
                        rs.getString("iban"),rs.getInt("funds"))
        );

        //check if there are multiple entries, already existing or it is non-existent
        if(temp.size()>1) {
            user = fixMultipleEntries(temp,user);
            for (int i = 1; i<temp.size();i++) {
                template.execute("delete from user where id=" + temp.get(i).getId());
            }

            saving = "update user set funds="+user.getFunds()+
                    " where name=\'"+user.getName()+"\' and iban=\'"+user.getIban()+"\'";

        }else if(temp.size()!=0){
            user.setFunds(temp.get(0).getFunds()+user.getFunds());

            saving = "update user set funds="+user.getFunds()+
                    " where name=\'"+user.getName()+"\' and iban=\'"+user.getIban()+"\'";

        }else {
            saving = "insert into user (iban,funds,name) values(\'"
                    +user.getIban()+"\',"+user.getFunds()+",\'"+user.getName()+"\')";
        }

        template.execute(saving);
    }

    //if there are multiple entries it takes funds and saves it in current user
    public User fixMultipleEntries(List<User> multiple,User currentUser){
        Integer funds =0;
        for(User temp:multiple)
            funds += temp.getFunds();

        currentUser.setFunds(currentUser.getFunds()+funds);

        return currentUser;
    }


}
