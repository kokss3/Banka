package koks.demo.Controller;

import koks.demo.Model.Account;
import koks.demo.Model.User;
import koks.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    public String runStart(){
        return "login";
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/index")
    public String login(ModelMap model){
        List<Account> accounts = service.getAccountListById(service.getId(getLoggedInUserName()));

        model.addAttribute("users",accounts);
        model.addAttribute("username",accounts.get(0).getRealName());
        return "index";
    }

    @Secured("ROLE_USER")
    @GetMapping("/transfer")
    public String runTransfer(ModelMap model){
        List<Account> accounts = service.getAccountListById(service.getId(getLoggedInUserName()));

        model.addAttribute("acc-holder", accounts.get(0));
        model.put("username", accounts.get(0).getRealName());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String sendFunds(@ModelAttribute("acc-holder") User user) {
        return "redirect:/index";
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
