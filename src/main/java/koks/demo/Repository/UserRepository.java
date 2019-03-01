package koks.demo.Repository;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    public User createUser(int id){
        User user = new User();
        String queryForAccounts = "select user_accounts.iban, user_accounts.funds from user_accounts " +
                "inner join auth_user on user_accounts.id = auth_user.id where auth_user.id=?;";

        String queryForCredentials = "select username, password from auth_user  where auth_user.id=?;";

        user.setId(id);
        user.setAccounts(template.query(
                queryForAccounts,new Object[]{id}, (rs, rowNum) ->
                        new Account(rs.getString("iban"), rs.getDouble("funds")
                )));

        user.setCredentials(template.query(
                queryForCredentials,new Object[]{id}, (rs, rowNum) ->
                        new AuthUser(rs.getString("username"),rs.getString("password"))
        ));
        return user;
    }
}
