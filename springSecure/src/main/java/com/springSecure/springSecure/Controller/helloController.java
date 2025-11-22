package com.springSecure.springSecure.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/welcomepage")
    public  String sayHello(){
        return "Hello world ";
    }
    @GetMapping("/hi")
    public  String sayhi(){
        return "HIIII ";
    }
}
