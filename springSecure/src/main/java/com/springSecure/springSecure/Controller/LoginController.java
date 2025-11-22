package com.springSecure.springSecure.Controller;

import com.springSecure.springSecure.dto.ApiResponse;
import com.springSecure.springSecure.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v3/user")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    ApiResponse<String>response=new ApiResponse<>();
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>>login(@RequestBody LoginDto loginDto){

        UsernamePasswordAuthenticationToken token=
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (authenticate.isAuthenticated()){
            response.setStatusCode(201);
            response.setMessage("Login is success");
            response.setData("Loggedin");
            return  new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
        }
        response.setData("Login failed");
        response.setMessage("cannot login");
        response.setStatusCode(400);
        return  new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
