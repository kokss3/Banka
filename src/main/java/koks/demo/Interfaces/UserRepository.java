package koks.demo.Interfaces;

import koks.demo.Model.User;

import java.util.List;

public interface UserRepository {

    User getUser(int id);
    User getUser(String username);
    List<User> findAllUsers();
    void saveUserToDB(User user);
}
