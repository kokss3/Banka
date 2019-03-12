package koks.demo.repository;

import koks.demo.interfaces.repos.AccountRepository;
import koks.demo.interfaces.repos.UserRepository;
import koks.demo.model.Account;
import koks.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate template;

    @Autowired
    AccountRepositoryImpl accountRepository;

    ///OVERLOADING METHODS
    //getUser by account-iban
    public User getUser(Account acc) {
        String queryForUserId = "select user_id from user_accounts where iban=?;";
        return getUser(template.queryForObject(queryForUserId, new Object[]{acc.getIban()}, Integer.class));
    }

    //getUser by username
    public User getUser(String username) {
        String queryForId = "select id from auth_user where username=?;";
        return getUser(template.queryForObject(queryForId, new Object[]{username}, Integer.class));
    }

    //getUser by id
    public User getUser(int id){
        User user = new User();

        //declare queries
        String queryForAccounts = "select user_accounts.id, user_accounts.iban, user_accounts.funds, " +
                                    "user_accounts.real_name from user_accounts inner join auth_user " +
                                    "on user_accounts.user_id = auth_user.id where auth_user.id=?;";
        String queryForUserName = "select auth_user.username from auth_user where id=?;";
        String queryForPassword= "select auth_user.password from auth_user where id=?;";


        //get id
        user.setId(id);

        //get list of accounts
        user.setAccounts(template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds"))));



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
            System.out.println(acc);
        }
        System.out.println(user);

        return user;
    }

    //save new User to DB
    @Override
    public void saveUserToDB(User user){
        String userCreds = "INSERT into user_accounts (id, username, password) values (?,?,?);";
        template.update(userCreds, user.getId(), user.getUsername(), user.getPassword());
    }

    //update funds by account, by iban
    public void updateFundsByAccount(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }
}
