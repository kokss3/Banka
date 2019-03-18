package koks.demo.controller;

import koks.demo.interfaces.controlers.controllerToServices;
import koks.demo.interfaces.repos.RoleRepository;
import koks.demo.model.Account;
import koks.demo.model.User;
import koks.demo.service.AccountServiceImpl;
import koks.demo.service.MyUserDetailsService;
import koks.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StarterController implements controllerToServices {


    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    AccountServiceImpl accountService;


    @GetMapping("/")
    public String runStart(){
        return "login";
    }


    @GetMapping(value = "/index")
    public String login(ModelMap model){
        List<Account> accounts = accountService.getAccountListById(userDetailsService.getId(getLoggedInUserName()));

        //Exception for new user
        try {
            model.addAttribute("username", accounts.get(0).getRealName());
            model.addAttribute("users", accounts);

        }catch (IndexOutOfBoundsException e){
            System.out.println(e);

            List<Account> temp = new ArrayList<>();
            temp.add(new Account(0,getLoggedInUserName(),"",0));

            model.addAttribute("username", getLoggedInUserName());
            model.addAttribute("users", temp);
        }
        return "index";
    }

    @GetMapping("/transfer")
    public String runTransfer(ModelMap model){
        List<Account> accounts = accountService.getAccountListById(userDetailsService.getId(getLoggedInUserName()));

        model.addAttribute("acc-holder", new Account());
        model.addAttribute("username", accounts.get(0).getRealName());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String sendFunds(@ModelAttribute("acc-holder") Account acc, @RequestParam(value="ibans") int statusId) {
        int id = userDetailsService.getId(getLoggedInUserName());
        Account senderAccount = getAccs(id).get(statusId-1);

        //subtract funds from sender
        senderAccount.setFunds(-acc.getFunds());
        accountService.updateAccount(senderAccount);

        //adding to recipient acc
        accountService.updateAccount(acc);

        return "redirect:/index";
    }

    @GetMapping("/register")
    public String postRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("register") User user){
        userDetailsService.createNewUser(user);

        //default role hardcoding
        Integer tempID = userDetailsService.getId(user.getUsername());
        userDetailsService.setRoles(tempID, RoleRepository.ROLE_USER);

        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminStuff(ModelMap model){
        int id = userDetailsService.getId(getLoggedInUserName());
        model.addAttribute("username", getAccs(id).get(0).getRealName());
        model.addAttribute("c_user", userDetailsService.getAll());
        return "admin";
    }

    @GetMapping("/add_user")
    public String addUser(ModelMap model){
        model.addAttribute("username", getLoggedInUserName());
        return "add_user";
    }

    @PostMapping("/add_user")
    public String addNewUser(@ModelAttribute("add_user") User user){
        userDetailsService.createNewUser(user);
        System.out.println(user.getRoles());

        Integer tempID = userDetailsService.getId(user.getUsername());

        if(user.getRoles().get(0).equalsIgnoreCase("ADMIN")){
            userDetailsService.setRoles(tempID, RoleRepository.ROLE_ADMINISTRATOR);
            userDetailsService.setRoles(tempID, RoleRepository.ROLE_USER);
        }else {
            userDetailsService.setRoles(tempID, RoleRepository.ROLE_USER);
        }
        return "redirect:/admin";
    }

    @GetMapping("/add_account")
    public String addAccount(ModelMap model){
        List<User> allUsers = userDetailsService.getAll();
        List<String> username = new ArrayList<>();

        for(User user:allUsers){
            username.add(user.getUsername());
        }

        model.addAttribute("userList", allUsers);
        model.addAttribute("account", new Account());
        model.addAttribute("getUser", new User());
        model.addAttribute("username", getLoggedInUserName());
        return "add_account";
    }

    @PostMapping("/add_account")
    public String addNewAccount(@ModelAttribute("account") Account acc, @ModelAttribute("getUser") User user){
        acc.setUser_id(userDetailsService.getId(user.getUsername()));
        accountService.saveNewAccount(acc);
        return "redirect:/index";
    }

    @GetMapping("/remove")
    public String remove(ModelMap model){
        List<User> allUsers = userDetailsService.getAll();
        List<Account> accounts = new ArrayList<>();

        for(User user:allUsers){
            accounts.addAll(user.getAccounts());
        }

        model.addAttribute("userList", allUsers);
        model.addAttribute("accounts", accounts);
        model.addAttribute("getAcc", new Account());
        model.addAttribute("getUser", new User());
        model.addAttribute("username", getLoggedInUserName());
        return "remove";
    }

    @PostMapping("/remove")
    public String removePost(@ModelAttribute("accounts") Account acc, @ModelAttribute("getUser") User user){

        try{
            accountService.removeAccount(accountService.getAccountByIban(acc.getIban()));
        }catch(EmptyResultDataAccessException e){
            System.out.println("No iban, do nothing");
        }catch (IndexOutOfBoundsException e){
            System.out.println("No iban, do nothing.");
        }

        try{
            user=userDetailsService.getUserByUsername(user.getUsername());

            for(Account remAcc:user.getAccounts()){
                accountService.removeAccount(remAcc);
            }
            userDetailsService.removeUser(user);

        }catch(EmptyResultDataAccessException e){
            System.out.println("No User, do nothing");
        }catch (IndexOutOfBoundsException e){
            System.out.println("No User, do nothing.");
        }

        return "redirect:/admin";
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
