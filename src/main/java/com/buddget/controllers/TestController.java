package com.buddget.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/free")
    public String free(){
        return "This is a free route";
    }

    @GetMapping("/locked")
    public String locked(){
        return "This is a locked route";
    }
}
