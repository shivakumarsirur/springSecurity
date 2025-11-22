package com.springSecure.springSecure.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class rbacController {
 @GetMapping("/api/v1/admin/welcome")
public String hello(){
    return  "hello form admin";
}

}
