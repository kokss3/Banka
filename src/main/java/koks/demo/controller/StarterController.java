package koks.demo.controller;

import koks.demo.interfaces.controlers.controllerToServices;
import koks.demo.model.Account;
import koks.demo.repository.RoleRepositoryImpl;
import koks.demo.service.AccountServiceImpl;
import koks.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class StarterController implements controllerToServices {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    RoleRepositoryImpl roleRepository;

    @Override
    @GetMapping("/")
    public String runStart(){
        return "login";
    }

    @Override
    @GetMapping(value = "/index")
    public String login(ModelMap model){
        List<Account> accounts = accountService.getAccountListById(userService.getId(getLoggedInUserName()));

        model.addAttribute("username", accounts.get(0).getRealName());
        model.addAttribute("users", accounts);
        return "index";
    }

    @GetMapping("/transfer")
    public String runTransfer(ModelMap model){
        List<Account> accounts = accountService.getAccountListById(userService.getId(getLoggedInUserName()));

        model.addAttribute("acc-holder", new Account());
        model.addAttribute("username", accounts.get(0).getRealName());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String sendFunds(@ModelAttribute("acc-holder") Account acc, @RequestParam(value="ibans") int statusId) {
        int id = userService.getId(getLoggedInUserName());
        Account senderAccount = getAccs(id).get(statusId-1);

        //subtract funds from sender
        senderAccount.setFunds(-acc.getFunds());
        accountService.saveAccount(senderAccount);

        //adding to recipient acc
        accountService.saveAccount(acc);

        return "redirect:/index";
    }

    @GetMapping("/admin")
    public String adminStuff(ModelMap model){
        int id = userService.getId(getLoggedInUserName());
        model.addAttribute("username", getAccs(id).get(0).getRealName());
        model.addAttribute("c_user", userService.getAll());
        return "admin";
    }

    private List<Account> getAccs(int id){
        return accountService.getAccountListById(id);
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
