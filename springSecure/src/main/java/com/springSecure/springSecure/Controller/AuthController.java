package com.springSecure.springSecure.Controller;

import com.springSecure.springSecure.dto.ApiResponse;
import com.springSecure.springSecure.dto.UserDto;
import com.springSecure.springSecure.model.UserObj;
import com.springSecure.springSecure.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v3/user")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserObj>>register(@RequestBody UserDto userDto){
        ApiResponse<UserObj> userObjApiResponse = authService.registerUser(userDto);
        return new ResponseEntity<>(userObjApiResponse, HttpStatus.valueOf(userObjApiResponse.getStatusCode()));

    }

}
