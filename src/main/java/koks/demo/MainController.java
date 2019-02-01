package koks.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/all")
    public List<String> getAllUsers(){
        return userRepository.getAllUsers();
    }

    @GetMapping(path = "/oib")
    public List<String> getAllOIBs(){
        return userRepository.getAllOIBs();
    }

    @GetMapping(path = "/names")
    public List<String> getAllAccounts(){
        return userRepository.getAllAccounts();
    }

}