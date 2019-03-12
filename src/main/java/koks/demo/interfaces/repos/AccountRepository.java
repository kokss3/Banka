package koks.demo.interfaces.repos;

import koks.demo.model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAccountListById(int id);
    void updateFundsByAccount(Account acc);
}
