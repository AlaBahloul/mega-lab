package com.example.SQLINJ.Controller;

import com.example.SQLINJ.Service.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller(value = "/auth")

@Controller
@RequestMapping("/SQL")
public class Mycontroller {

    @Autowired
    Auth aa = new Auth();

    @GetMapping("/login")
    public String  connection (){

        return "SQLINJ/login" ;
    }
    @PostMapping("/tester")
    public String test(Model model ,String login , String password){

        String l = login;
        String p = password;
        boolean test = aa.connection(l,p);

        if (test){
            return "SQLINJ/success";
        }else {
            model.addAttribute("test",test);
            //return "redirect:/login";
            return "SQLINJ/relogin";
        }
    }



}
