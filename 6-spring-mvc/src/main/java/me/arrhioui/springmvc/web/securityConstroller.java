package me.arrhioui.springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityConstroller {

    @GetMapping("/403")
    public String notAuthorized(){
        return "403";
    }
}
