package koks.demo.Controller;

import koks.demo.Model.Account;
import koks.demo.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class StarterController {

    @Autowired
    UserServiceImpl service;

    @GetMapping("/")
    public String runStart(){
        return "login";
    }

    @GetMapping(value = "/index")
    public String login(ModelMap model){
        List<Account> accounts = service.getAccountListById(service.getId(getLoggedInUserName()));

        model.addAttribute("username", accounts.get(0).getRealName());
        model.addAttribute("users", accounts);
        return "index";
    }

    @GetMapping("/transfer")
    public String runTransfer(ModelMap model, @RequestParam(value="ibans") int statusId){
        List<Account> accounts = getAccs(service.getId(getLoggedInUserName()));

        model.addAttribute("acc-holder", new Account());
        model.addAttribute("username", accounts.get(0).getRealName());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String sendFunds(@ModelAttribute("acc-holder") Account acc, @RequestParam(value="ibans") int statusId) {
        int id = service.getId(getLoggedInUserName());
        Account senderAccount = service.getAccountListById(id).get(statusId-1);

        //subtract funds from sender
        senderAccount.setFunds(-acc.getFunds());
        service.saveAccount(senderAccount);

        //adding to recipient acc
        service.saveAccount(acc);

        return "redirect:/index";
    }

    @GetMapping("/admin")
    public String adminStuff(ModelMap model){
        int id = service.getId(getLoggedInUserName());
        model.addAttribute("username",service.getAccountListById(id).get(0).getRealName());
        model.addAttribute("c_user", service.getAll());
        return "admin";
    }

    private List<Account> getAccs(int id){
        return service.getAccountListById(id);
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
