package com.springSecure.springSecure.service;

import com.springSecure.springSecure.dto.ApiResponse;
import com.springSecure.springSecure.dto.UserDto;
import com.springSecure.springSecure.model.UserObj;
import com.springSecure.springSecure.repository.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder encoder;

    public ApiResponse<UserObj> registerUser(UserDto userDto){
        ApiResponse<UserObj>response=new
                ApiResponse<>();
      if ( userRepo.existsByName(userDto.getName())){
          response.setStatusCode(400);
          response.setMessage("User name exists Already By userName");
          response.setData(null);
          return  response;
      }
        if ( userRepo.existsByEmail(userDto.getEmail())){
            response.setStatusCode(400);
            response.setMessage("User name exists Alredy in email");
            response.setData(null);
            return  response;
        }
        UserObj userone=new UserObj();
        BeanUtils.copyProperties(userDto,userone,"id");
        userone.setPassword(encoder.encode(userDto.getPassword()));
         userRepo.save(userone);
        response.setStatusCode(200);
        response.setMessage("user is created");
        response.setData(userone);
        return response;
    }


}
