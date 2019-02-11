package koks.demo.Service;

import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getIban(String name){
        return repository.findByName(name);
    }

    public List<User> getFunds(String iban){
        return repository.findFundsByIban(iban);
    }




}
