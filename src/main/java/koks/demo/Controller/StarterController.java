package koks.demo.Controller;

import koks.demo.Model.User;
import koks.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
public class StarterController {

    @Autowired
    UserService service;

    @GetMapping("/")
    public String runStart(ModelMap model){
        model.put("name", getLoggedInUserName());
        model.put("users", getByName(getLoggedInUserName()));
        return "index";
    }

    @GetMapping("/login")
    public String login(ModelMap model){
        model.put("name", getLoggedInUserName());

        return "login";
    }

    private List<User> getByName (String name){
        return service.getListByName(name);
    }

    @GetMapping("/transfer")
    public String runTransfer(ModelMap model){
        model.addAttribute("acc-holder", new User());

        model.put("name", getLoggedInUserName());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String sendFunds(@ModelAttribute("acc-holder") User user) {
        User loggedUser = service.getListByName(getLoggedInUserName()).get(0);

        loggedUser.setFunds(-user.getFunds());

        service.saveInDB(loggedUser);
        service.saveInDB(user);
        return "redirect:/";
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
