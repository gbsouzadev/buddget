package com.buddget.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("login")
    public String doLogin() {
        return "\t\t<form action=\"/auth/login\" method=\"post\">\n" +
                "\t\t\t<div>\n" +
                "\t\t\t<input type=\"text\" name=\"email\" placeholder=\"Username\"/>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div>\n" +
                "\t\t\t<input type=\"password\" name=\"password\" placeholder=\"Password\"/>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<input type=\"submit\" value=\"Log in\" />\n" +
                "\t\t</form>";
    }
}
