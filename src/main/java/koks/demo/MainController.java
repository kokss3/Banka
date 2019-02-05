package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping()
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/all")
    public List getAllUsers(Integer id){
                return userRepository.findAll();
    }

    @GetMapping(path = "/{iban}")
    public List getUser(@PathVariable String iban){
        return userRepository.showSpecific(iban);
    }

    @GetMapping(path = "/sum/{iban}")
    public User getAllFunds(@PathVariable String iban){
        return userRepository.sumAllFunds(iban);
    }


}