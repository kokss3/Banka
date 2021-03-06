package koks.demo.service;

import koks.demo.interfaces.services.AccountService;
import koks.demo.model.Account;
import koks.demo.repository.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepositoryImpl repository;

    @Override
    public List<Account> getAccountListById(int id) {
        return repository.findAccountListById(id);
    }

    @Override
    public Account getAccountByIban(String iban) {
        return repository.findAccountByIban(iban);
    }

    @Override
    public void updateAccount(Account acc) {
        repository.updateFundsByAccount(acc);
    }

    @Override
    public void removeAccount(Account acc) {
        repository.removeAccount(acc);
    }

    @Override
    public void saveNewAccount(Account acc) {
        repository.crateAccount(acc);
    }
}
