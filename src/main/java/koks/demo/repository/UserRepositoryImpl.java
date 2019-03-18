package koks.demo.repository;

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
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    JdbcTemplate template;

    @Autowired
    AccountRepositoryImpl accountRepository;

    @Autowired
    RoleRepositoryImpl roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //getUser by username
    public User getUser(String username) {
        String queryForId = "select id from user_auth where username=?";
        int id = template.queryForObject(queryForId, new Object[]{username}, Integer.class);
        return getUser(id);
    }

    //getUser by id
    public User getUser(int id){
        User user = new User();

        //declare queries
        String queryForUserName = "select user_auth.username from user_auth where id=?;";
        String queryForPassword= "select user_auth.password from user_auth where id=?;";

        user.setId(id);

        //prepare username and password
        user.setUsername(template.queryForObject(queryForUserName, new Object[]{id}, String.class));
        user.setPassword(template.queryForObject(queryForPassword, new Object[]{id}, String.class));

        user.setAccounts(accountRepository.findAccountListById(id));

        //prepare roles
        user.setRoles(roleRepository.getRolesById(id));

        return user;
    }

    @Override
    public Integer getUserId(String username) {
        String queryForId = "select id from user_auth where username=?;";
        return template.queryForObject(queryForId, new Object[]{username}, Integer.class);
    }

    //get all users in database
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(template.query("select * from user_auth;",
                (rs, rowNum) ->  new User(
                        (rs.getInt("id")),
                        rs.getString("username"),
                        rs.getString("password"))
        ));

        for(int i = 0; i<users.size();i++){
            User user = users.get(i);
            List<Account> acc = accountRepository.findAccountListById(user.getId());
            user.setAccounts(acc);
            user.setRoles(roleRepository.getRolesById(user.getId()));
        }
        return users;
    }

    //save new User to DB
    @Override
    public void saveUserToDB(User user) {
        String userCreds = "INSERT into user_auth (username, password) values (?,?);";
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        template.update(userCreds, user.getUsername(), encryptedPassword);
    }

    @Override
    public void removeUser(User user) {
        String removeRole="delete from user_auth where id=? ";
        template.update(removeRole,user.getId());
    }

    @Override
    public void updateUser(User user){
        String updateCommand = "update user_auth set username=?, password=? where id=?";
        template.update(updateCommand, user.getUsername(),
                bCryptPasswordEncoder.encode(user.getPassword()),user.getId());
    }
}
