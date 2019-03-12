package koks.demo.interfaces.services;

import koks.demo.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccountListById(int id);
    void saveAccount(Account acc);
}
