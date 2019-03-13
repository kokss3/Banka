package koks.demo.repository;

import koks.demo.interfaces.repos.RoleRepository;
import koks.demo.interfaces.repos.UserRepository;
import koks.demo.model.Account;
import koks.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository, RoleRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    AccountRepositoryImpl accountRepository;

    //getUser by username
    public User getUser(String username) {
        String queryForId = "select id from auth_user where username=?;";
        return getUser(template.queryForObject(queryForId, new Object[]{username}, Integer.class));
    }

    //getUser by id
    public User getUser(int id){
        User user = new User();

        //declare queries
        String queryForUserName = "select auth_user.username from auth_user where id=?;";
        String queryForPassword= "select auth_user.password from auth_user where id=?;";

        //get id
        user.setId(id);

        //get username and password
        user.setUsername(template.queryForObject(queryForUserName, new Object[]{id}, String.class));
        user.setPassword(template.queryForObject(queryForPassword, new Object[]{id}, String.class));

        return user;
    }

    @Override
    public Integer getUserId(String username) {
        String queryForId = "select id from auth_user where username=?;";
        return template.queryForObject(queryForId, new Object[]{username}, Integer.class);
    }

    //get all users in database
    public List<User> findAllUsers() {
        List<User> user = new ArrayList<>();
        user.addAll(template.query("select * from auth_user;",
                (rs, rowNum) ->  new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"))
        ));

        for(int i = 0; i<user.size();i++){
            List<Account> acc = accountRepository.findAccountListById(user.get(i).getId());
            user.get(i).setAccounts(acc);
        }

        return user;
    }

    //save new User to DB
    @Override
    public void saveUserToDB(User user){
        String userCreds = "INSERT into auth_user (username, password) values (?,?);";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        template.update(userCreds, user.getUsername(), encryptedPassword);
    }

    @Override
    public List<String> getRolesById(int id) {
        String getRoles="select * from ;";

        return template.query(getRoles,new Object[] {id},
                (rs, rowNum)->(rs.getString("id")));
    }

    @Override
    public void setRoles(Integer id, Integer roleNumber) {

    }
}
