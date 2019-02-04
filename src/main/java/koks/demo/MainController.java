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

    @GetMapping(path = "/{id}")
    public Map getAllUsers(Integer id){
                return userRepository.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public Map getUser(String a,Integer id){
        return null;
    }

    @GetMapping(path = "/oib")
    public List<String> getAllOIBs(){
        return userRepository.getAllOIBs();
    }
}