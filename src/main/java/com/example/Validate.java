package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
//@RequestMapping()
public class Validate {

    @GetMapping("/success")
    public String success (){

        return "success";
    }

    @GetMapping("/validate")
    public String validate(String flag){
        System.out.println(flag);

        if(Objects.equals(flag, "IDOR_Com_Exect77548")){
            return "success";
        }
        return"validate";

    }









}
