package koks.demo.interfaces.repos;

import koks.demo.model.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findAccountListById(int id);
    Account findAccountByIban(String iban);
    void updateFundsByAccount(Account acc);
    void crateAccount(Account acc);
    void removeAccount(Account acc);
}
