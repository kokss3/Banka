package koks.demo.repository;

import koks.demo.interfaces.repos.AccountRepository;
import koks.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Account> findAccountListById(int id){
        String queryForAccounts = "select user_accounts.id, user_accounts.iban, user_accounts.funds, " +
                "user_accounts.real_name from user_accounts inner join auth_user " +
                "on user_accounts.user_id = auth_user.id where auth_user.id=?;";

        return template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds")));
    }

    @Override
    public void updateFundsByAccount(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }
}
