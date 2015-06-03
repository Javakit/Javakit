package net.compitek.javakit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;



@Controller
public class SimpleController {

    @RequestMapping(value={"/","/hello"})
    public String Hello(HttpServletRequest request){
        return "hello";
    }
}
