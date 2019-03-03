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

    public Integer getIdformLoggedUser(String username){
        String query = "select id from auth_user where username=?;";
        return template.queryForObject(query, new Object[]{username}, Integer.class);
    }

    public List<Account> getAccountListById(int id){
        List<Account> acc = new ArrayList<>();
        String queryForAccounts =
                "select user_accounts.iban, user_accounts.funds, user_accounts.real_name " +
                "from user_accounts inner join auth_user on user_accounts.user_id = auth_user.id where auth_user.id=?;";
        acc.addAll(template.query(
                queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                        new Account(rs.getString("real_name"),
                                rs.getString("iban"), rs.getDouble("funds"))));
        return acc;
    }

    public List<String> getRolesById(int id){
        List<String> roles = new ArrayList<>();
        String queryForAccounts =
                "select role_user.role from role_user inner join auth_user " +
                        "on role_user.user_id = auth_user.id where auth_user.id=?;";
        return template.query(queryForAccounts, new Object[]{ id },
                (rs, rowNum) -> rs.getString("role"));
    }

    public String getUsernameById(int id){
        String queryForUserName = "select auth_user.username from auth_user where id=?;";
        return template.queryForObject(queryForUserName,new Object[]{id},String.class);
    }

    public String getPasswordById(int id) {
        String queryForPassword = "select auth_user.password from auth_user where id=?;";
        return template.queryForObject(queryForPassword, new Object[]{id}, String.class);
    }

    public List<User> findAll() {
        List<Integer> ids = new ArrayList<>();
        List<User> users = new ArrayList<>();

        ids.addAll(template.query("select id from auth_user;",
                (rs, rowNum) -> rs.getInt("id")));
        for(Integer id: ids) {
            users.add(getUserbyId(id));
            System.out.println(id);
        }
        return users;
    }

    public User getUserbyId(int id){
        User user = new User();
        user.setId(id);
        user.setAccounts(getAccountListById(id));
        user.setUsername(getUsernameById(id));
        user.setPassword(getPasswordById(id));
        user.setRoles(getRolesById(id));
        return user;
    }

}
