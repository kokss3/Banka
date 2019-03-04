package koks.demo.Repository;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    //overloading methods
    public User getUser(String username) {
        String queryForId = "select id from auth_user where username=?;";
        return getUser(template.queryForObject(queryForId, new Object[]{username}, Integer.class));
    }

    public User getUser(int id){
        User user = new User();

        //declare queries
        String queryForAccounts = "select user_accounts.iban, user_accounts.funds, user_accounts.real_name " +
                                    "from user_accounts inner join auth_user " +
                                    "on user_accounts.user_id = auth_user.id where auth_user.id=?;";
        String queryForUserName = "select auth_user.username from auth_user where id=?;";
        String queryForPassword= "select auth_user.password from auth_user where id=?;";
        String queryForUserRoles = "select role_user.role from role_user inner join auth_user " +
                                        "on role_user.user_id = auth_user.id where auth_user.id=?;";

        //get id
        user.setId(id);

        //get list of accounts
        user.setAccounts(template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds"))));

        //get roles
        user.setRoles(template.query(queryForUserRoles, new Object[]{ id },
                (rs, rowNum) -> rs.getString("role")));

        //get username and password
        user.setUsername(template.queryForObject(queryForUserName, new Object[]{id}, String.class));
        user.setPassword(template.queryForObject(queryForPassword, new Object[]{id}, String.class));

        System.out.println(user);
        return user;
    }

    public List<User> findAll() {
        List<Integer> ids = new ArrayList<>();
        List<User> users = new ArrayList<>();

        ids.addAll(template.query("select id from auth_user;",
                (rs, rowNum) -> rs.getInt("id")));
        for(Integer id: ids) {
            users.add(getUser(id));
        }
        return users;
    }
}
