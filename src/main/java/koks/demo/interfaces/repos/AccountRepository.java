package koks.demo.interfaces.repos;

import koks.demo.model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAccountListById(int id);
    boolean hasRealName(String realName);
    void updateFundsByAccount(Account acc);
    void crateAccount(Account acc, Integer id);
}
