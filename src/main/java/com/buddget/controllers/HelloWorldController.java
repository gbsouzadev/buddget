package com.buddget.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public ResponseEntity<String> index() {
        String string = "Hello world!";
        return ResponseEntity.ok().body(string);
    }
}