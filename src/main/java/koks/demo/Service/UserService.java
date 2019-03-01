package koks.demo.Service;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    User user = new User();

    @Autowired
    UserRepository repository;

    private User getCompleteUser(Integer id){
        user.setId(id);
        user.setUsername(repository.getUsernameById(id));
        user.setPassword(repository.getPasswordById(id));
        user.setAccounts(repository.getAccountListById(id));
        user.setRole(repository.getRolesById(id));
        return user;
    }



    public int getId(String username){
        return repository.getIdformLoggedUser(username);
    }

    public List<String> getRoles(int id){
        return repository.getRolesById(id);
    }

    public List<Account> getAccountListById(int id) {
        return repository.getAccountListById(id);
    }

}
