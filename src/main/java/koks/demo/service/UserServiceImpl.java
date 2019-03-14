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
    UserRepositoryImpl repository;

    @Autowired
    RoleRepositoryImpl roleRepository;

    @Override
    public Integer getId(String username){
        return repository.getUser(username).getId();
    }

    @Override
    public void createNewUser(User user){
        repository.saveUserToDB(user);
    }

    @Override
    public void removeUser(User user) {
        repository.removeUser(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAllUsers();
    }

    @Override
    public void setRoles(Integer id, Integer roleNumber) {
        roleRepository.setRoles(id,roleNumber);
    }

    @Override
    public List<String> getRolesById(Integer id) {
        return roleRepository.getRolesById(id);
    }

    @Override
    public void removeRole(User user) {
      roleRepository.removeRole(user);
    }

}
