package com.example.Timebased.Controller;

import com.example.Timebased.TrackingId;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

@Controller
public class finalController {

    TrackingId trackId = new TrackingId() ;
    boolean Firstaccess = true;

    public Cookie setCoockie(){

        String val = trackId.getusername("administrator");
        Cookie coock = new Cookie("tracking_ID",val);

        return coock;

    }

//    @GetMapping("/home")
//    public String thisone(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {
//
//        if(Firstaccess){
//            Firstaccess=false;
//            setCoockie().setMaxAge(60); // Set max age to one hour (in seconds)
//            setCoockie().setPath("/one"); // Deliver to the specified URL and its subdirectories
//            response.addCookie(setCoockie());
//
//            return "welcome";
//
//        }
//
//        else {
//
//            if (Objects.equals(trackId.getTrackingid(getcoock(request)), "fg58rdsf")) {
//
//                return "welcome_custom";
//
//
//            } else {
//
//                return "welcome_back";
//            }
//        }
//    }



    public String getcoock(HttpServletRequest rq ) throws UnsupportedEncodingException {

        for (Cookie co : rq.getCookies()) {
            if (co.getName().equals("tracking_ID")) {
                return URLDecoder.decode(co.getValue(), "UTF-8");
            }
        }
        return "fg58rdsf";

    }

    @PostMapping("/tester")
    public String test(Model model , String login , String password){

        boolean test = trackId.authentification(login,password);
        if (test){return "redirect:/ping";}else{
        return "Timebased/login";}
    }

    @GetMapping("/login")
    public String  connection (){

        return "Timebased/login" ;
    }

    @GetMapping("/home")
    public String thisone(HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException {

        if(Firstaccess){
            Firstaccess=false;
            setCoockie().setMaxAge(60); // Set max age to one hour (in seconds)
            setCoockie().setPath("/one"); // Deliver to the specified URL and its subdirectories
            response.addCookie(setCoockie());

            return "Timebased/home";

        }

        else {

            if (Objects.equals(trackId.getTrackingid(getcoock(request)), "fg58rdsf")) {

                return "Timebased/home_custom";


            } else {

                return "Timebased/home_back";
            }
        }
    }

}
