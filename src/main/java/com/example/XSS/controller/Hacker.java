package com.example.XSS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Hacker {
    List<String> list = new ArrayList<>();
    boolean fois = true;
    @GetMapping("/hack")
    public String hackerPage(Model model,String steal) {

        if(fois) {
            list.add("You will find here the data sent");
            fois = false;
        }
        list.add(steal);
        model.addAttribute("list", list);

        return "XSS/hacker";
    }
}
