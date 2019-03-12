package koks.demo.Repository;

import koks.demo.Model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate template;

    public List<Account> findAccountListById(int id){
        String queryForAccounts = "select user_accounts.id, user_accounts.iban, user_accounts.funds, " +
                "user_accounts.real_name from user_accounts inner join auth_user " +
                "on user_accounts.user_id = auth_user.id where auth_user.id=?;";

        return template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds")));
    }

    public void updateFundsByAccount(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }
}
