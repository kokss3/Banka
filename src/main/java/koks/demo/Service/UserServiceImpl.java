package koks.demo.Service;

import koks.demo.Interfaces.UserService;
import koks.demo.Model.Account;
import koks.demo.Model.User;
import koks.demo.Repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl repository;

    @Override
    public Integer getId(String username){
        return repository.getUser(username).getId();
    }

    public User getUserByAccount(Account acc){
        return repository.getUser(acc);
    }

    public List<String> getRoles(int id){
        return repository.getUser(id).getRoles();
    }

    public List<Account> getAccountListById(int id) {
        return repository.getUser(id).getAccounts();
    }

    @Override
    public List<User> getAll() {
        return repository.findAllUsers();
    }

    public void saveAccount(Account acc){
        repository.updateFundsByIban(acc);
    }
}
