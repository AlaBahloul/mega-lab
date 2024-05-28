package com.example.XSS.controller;

import com.example.XSS.Connection;
import com.example.XSS.Entity.Institution;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/XSS")
public class InstController {

    boolean isAuthenticated =false ;

    public List<Institution> institutions = new ArrayList<>();
    public List<Institution> institutionsala = new ArrayList<>();
    Connection conn = new Connection();
    private Institution i1 = new Institution("FSEGS", "Faculté à Sfax", "Sfax");
    private Institution i2 = new Institution("ISIMS", "Institut à Sfax", "Sfax");
    private Institution i3 = new Institution("test", "test", "<script>alert(\"Next lab Credentials: ala ; ala\"); </script>");
//    public List<Institution> getInstitutions() {
//
//        institutions.add(i1);
//        institutions.add(i2);
//
//        return institutions;
//    }
    boolean fois = true;

     @GetMapping(value = "/get")
    public String showInstitutions(Model model,String name,String description,String state,HttpServletRequest request) {

         if (isAuthenticated) {
             if (fois) {
                 institutions.add(i1);
                 institutions.add(i2);
                 institutionsala.add(i1);
                 institutionsala.add(i2);
                 fois = false;
             }
             if (name != null & description != null & state != null) {
                 Institution rec = new Institution(name, description, state);
                 institutions.add(rec);
                 institutionsala.add(rec);
             }
             HttpSession session = request.getSession(false);
             String theusername = session.getAttribute("username").toString();

             model.addAttribute("institutions", institutions);
             //model.addAttribute("description", description);
             model.addAttribute("username", theusername);
            if(theusername.equals("admin")){return "XSS/instituadmin";}
            else if (theusername.equals("ala")) {

            institutionsala.add(i3);
                model.addAttribute("institutions", institutionsala);
                return "XSS/instituala";

            } else{ return "XSS/institu";}
         }
         else{return "redirect:/login";}
     }

    @GetMapping("/vulnerable")
    public String vulnerablePage(@RequestParam(name = "userInput", required = false) String userInput, Model model) {



        model.addAttribute("userInput", userInput);
        return "vulnerable";


    }


    @GetMapping("/login")
    public String login (HttpServletRequest request,HttpServletResponse response){

         //rajaaaaaaaahaaaaaahah
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                cookie.setMaxAge(0); // Set the cookie's expiration time to 0 (expired)
//                cookie.setPath("/"); // Set the cookie's path to the root path
//                response.addCookie(cookie); // Add the expired cookie to the response
//            }
//        }
        return "XSS/login";
    }

    @PostMapping("/tester")
    public String test(String login , String password, HttpServletRequest request, HttpServletResponse response){
         isAuthenticated = conn.authentification(login,password);

        if (isAuthenticated){

            HttpSession session = request.getSession(true);
            session.setAttribute("username", login);

            //Cookie coockie0 = new Cookie("Session", session.getId());

            //response.addCookie(coockie0);
            //if (login.equals("admin")){return "instituadmin";}
            return "redirect:/XSS/get";}


        else{
            return "redirect:/XSS/login";}
    }

    @GetMapping("/success")
    public String success(){

         return "XSS/success";
    }
}
