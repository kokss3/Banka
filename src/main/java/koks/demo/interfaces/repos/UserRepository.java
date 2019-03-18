package koks.demo.interfaces.repos;

import koks.demo.model.User;

import java.util.List;

public interface UserRepository {

    Integer getUserId(String username);
    User getUser(int id);
    User getUser(String username);
    List<User> findAllUsers();
    void saveUserToDB(User user);
    void removeUser(User user);
    void updateUser(User user);
}
