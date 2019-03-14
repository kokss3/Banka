package koks.demo.interfaces.services;

import koks.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> getAll();

    Integer getId(String username);

    void createNewUser(User user);

    void removeUser(User user);

}
