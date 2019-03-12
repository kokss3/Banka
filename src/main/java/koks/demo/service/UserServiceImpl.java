package koks.demo.service;

import koks.demo.interfaces.services.UserService;
import koks.demo.model.User;
import koks.demo.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepositoryImpl repository;


    @Override
    public Integer getId(String username){
        return repository.getUser(username).getId();
    }

    public List<String> getRoles(int id){
        return repository.getUser(id).getRoles();
    }

    @Override
    public List<User> getAll() {
        return repository.findAllUsers();
    }

}
