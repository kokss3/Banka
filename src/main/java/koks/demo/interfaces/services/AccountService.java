package koks.demo.interfaces.services;

import koks.demo.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccountListById(int id);
    Account getAccountByIban(String iban);
    void saveNewAccount(Account acc);
    void updateAccount(Account acc);
    void removeAccount(Account acc);
}
