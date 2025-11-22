package com.springSecure.springSecure.Controller;

import com.springSecure.springSecure.dto.ApiResponse;
import com.springSecure.springSecure.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LoginCOntroller {
    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginCOntroller(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse>login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken token=
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(),loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        return  null;
    }
}
