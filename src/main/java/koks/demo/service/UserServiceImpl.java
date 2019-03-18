package koks.demo.service;

import koks.demo.interfaces.services.RoleService;
import koks.demo.interfaces.services.UserService;
import koks.demo.model.User;
import koks.demo.repository.RoleRepositoryImpl;
import koks.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements RoleService, UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    @Autowired
    RoleRepositoryImpl roleRepository;

    @Override
    public Integer getId(String username){
        return userRepository.getUser(username).getId();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUser(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public void createNewUser(User user){
        userRepository.saveUserToDB(user);
    }

    @Override
    public void removeUser(User user) {
        userRepository.removeUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAllUsers();
    }

    @Override
    public List<String> getRolesById(Integer id) {
        return roleRepository.getRolesById(id);
    }

    @Override
    public void setRoles(Integer id, Integer roleNumber) {
        roleRepository.setRoles(id,roleNumber);
    }

    @Override
    public void removeRole(User user, String role) {
      roleRepository.removeRole(user, role);
    }

}
