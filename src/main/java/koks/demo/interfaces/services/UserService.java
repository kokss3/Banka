package koks.demo.interfaces.services;

import koks.demo.model.User;
import java.util.List;

public interface UserService {

    List<User> getAll();

    Integer getId(String username);

    User getUserById(int id);

    User getUserByUsername(String username);

    void createNewUser(User user);

    void removeUser(User user);

    void updateUser(User user);

}
