package koks.demo.repository;

import koks.demo.interfaces.repos.AccountRepository;
import koks.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Account> findAccountListById(int id){
        String queryForAccounts = "select user_accounts.id, user_accounts.iban, user_accounts.funds, " +
                "user_accounts.real_name from user_accounts inner join user_auth " +
                "on user_accounts.user_id = user_auth.id where user_auth.id=?;";

        return template.query(queryForAccounts,new Object[]{ id }, (rs, rowNum) ->
                new Account(rs.getInt("id"),rs.getString("real_name"),
                        rs.getString("iban"), rs.getDouble("funds")));
    }

    @Override
    public boolean hasRealName(String realName) {
        int  value;
        String checkFor = "SELECT IF( EXISTS(SELECT COUNT(*) FROM user_accounts WHERE real_name = ? ),1,0)";
        value = template.query(checkFor, new Object[]{realName}, (rs, rowNum) -> (rs.getInt(0))).get(0);
        return value > 0;
    }

    @Override
    public void updateFundsByAccount(Account acc){
        String updateString = "update user_accounts set funds = funds + ? where user_accounts.iban=?";
        template.update(updateString, acc.getFunds(), acc.getIban());
    }

    @Override
    public void crateAccount(Account acc, Integer id) {
        String createString = "insert into user_accounts (user_id,iban,funds,real_name) values(?,?,?,?)";
        template.update(createString, id, acc.getIban(), acc.getFunds(), acc.getRealName());

    }

    @Override
    public void removeAccount(Account acc) {
        String removeCommand = "delete from user_accounts where id=?;";
        template.update(removeCommand, acc.getId());
    }
}
