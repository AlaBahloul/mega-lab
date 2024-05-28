package com.example.IDOR.Controller;

//import com.example.IDOR.Connection;
import com.example.IDOR.Entity.IDORUser;
//import com.example.IDOR.Repository.UserRepository;
import com.example.IDOR.Connection;
import com.example.IDOR.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/IDOR")
public class UserController {


    @Autowired
    UserRepository userrepo;
    Connection conn = new Connection();
    boolean isAuthenticated =false;
    @GetMapping("/index")
    public String test(Model model,HttpServletRequest request) {
        if (isAuthenticated){
            HttpSession session = request.getSession(false);
            String theuser=session.getAttribute("username").toString();
            model.addAttribute("username", theuser);
            int idd= userrepo.findByUsername(theuser).getId();
            String id = Integer.toString(idd);
            model.addAttribute("id",id );
        return "IDOR/index";}else{
            return "redirect:/login";}
    }



    @GetMapping("/login")
    public String login (HttpServletRequest request, HttpServletResponse response){

        return "IDOR/login";
    }

    @PostMapping("/tester")
    public String test(String login , String password, HttpServletRequest request, HttpServletResponse response,Model model){
        isAuthenticated = conn.authentification(login,password);

        if (isAuthenticated){

            HttpSession session = request.getSession(true);
            session.setAttribute("username", login);
            session.setAttribute("password", password);
            int idd= userrepo.findByUsername(login).getId();
            String id = Integer.toString(idd);
            model.addAttribute("id",id );


            //Cookie coockie0 = new Cookie("Session", session.getId());

            //response.addCookie(coockie0);
            if (login.equals("admin")){return "redirect:/home";}
            return "redirect:/IDOR/index";}else{
            return "redirect:/IDOR/login";}
    }

    @GetMapping("/details")
    public String details(Model model, HttpServletRequest request,@RequestParam("id") String id) {



            HttpSession session = request.getSession(false);
            String theuser = session.getAttribute("username").toString();
        System.out.println(theuser);

            IDORUser u1= userrepo.findById(Integer.parseInt(id));
        System.out.println(u1);
            model.addAttribute("user", u1);
            model.addAttribute("test", "alaa");
            return "IDOR/details";


    }

}
