package koks.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ViewController {

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("satnica", new Date());
        model.addAttribute("username","Koks");
        model.addAttribute("mode","development");
        return "index";
    }



}