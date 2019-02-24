package koks.demo.Service;

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

    @Autowired
    UserRepository repository;

    public List<User> getListByName(String name) {
        return repository.getListByName(name);
    }

    //removes multiple entries, and saves new entry, or updates old
    public void saveInDB(User user) {
        repository.saveInDB(user);
    }
}
