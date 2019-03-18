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
        String queryForAccounts = "select user_accounts.user_id, user_accounts.iban, user_accounts.funds, " +
                "user_accounts.real_name from user_accounts inner join user_auth " +
                "on user_accounts.user_id = user_auth.id where user_auth.id=?;";

        return template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("user_id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds")));
    }

    @Override
    public Account findAccountByIban(String iban) {
        String queryForAccounts = "select user_accounts.id, user_accounts.funds, " +
                "user_accounts.real_name from user_accounts inner join user_auth " +
                "on user_accounts.user_id = user_auth.id where user_accounts.iban=?;";

        return template.query(queryForAccounts,new Object[]{ iban }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        iban, rs.getDouble("funds"))).get(0);
    }

    @Override
    public void updateFundsByAccount(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }

    @Override
    public void crateAccount(Account acc) {
        String createString = "insert into user_accounts (user_id,iban,funds,real_name) values(?,?,?,?)";
        template.update(createString, acc.getUser_id(), acc.getIban(), acc.getFunds(), acc.getRealName());

    }

    @Override
    public void removeAccount(Account acc) {
        String removeCommand = "delete from user_accounts where iban=?;";
        template.update(removeCommand, acc.getIban());
    }
}
