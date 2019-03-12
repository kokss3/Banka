package koks.demo.interfaces.services;

import koks.demo.model.User;
import java.util.List;

public interface UserService {

    /*
     * Get all Users
     * @return List
     */
    List<User> getAll();

    /*
     * Get Id from database
     * @param username
     * @return Integer
     */
    Integer getId(String username);

}
