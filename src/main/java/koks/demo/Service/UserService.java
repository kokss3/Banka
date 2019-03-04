package koks.demo.Service;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public int getId(String username){
        return repository.getUser(username).getId();
    }

    public List<String> getRoles(int id){
        return repository.getUser(id).getRoles();
    }

    public List<Account> getAccountListById(int id) {
        return repository.getUser(id).getAccounts();
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public void saveAccount(Account acc){
        repository.updateFunds(acc);
    }
}
