package koks.demo.Controller;

import koks.demo.Model.User;
import koks.demo.Repository.UserRepository;
import koks.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StarterController {

    @Autowired
    UserService service;


    @GetMapping("/")
    public String runStart(ModelMap model){
        model.put("name", getLoggedInUserName());
        return "index";
    }

    private List<User> getByName (String name){
        return service.getIban(name);
    }

    @GetMapping(value="/transfer")
    public String runTransfer(ModelMap model){
        model.put("name", getLoggedInUserName());
        model.put("users", getByName(getLoggedInUserName()));
        return "transfer";
    }

    private String getLoggedInUserName(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }
        return principal.toString();
    }
}
