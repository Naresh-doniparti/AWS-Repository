package com.microservices.aws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloWorldController {

    @Autowired(required = false)
    private String messageFromGit;

    @GetMapping("/message")
    public ResponseEntity<String> getMessageFromConfig(){
        String message = messageFromGit!= null ? messageFromGit: "Hello";
        System.out.println(message);
        return ResponseEntity.ok().body(message);
    }
}
