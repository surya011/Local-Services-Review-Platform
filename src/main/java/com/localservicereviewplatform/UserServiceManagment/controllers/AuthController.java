package com.localservicereviewplatform.UserServiceManagment.controllers;

import com.localservicereviewplatform.UserServiceManagment.dtos.LoginRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.LogoutRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.SignUpRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import com.localservicereviewplatform.UserServiceManagment.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviewsystem")
public class AuthController {


    private final  UserService userService;

    public AuthController(@Qualifier("UserServiceImplementation") UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserDto> registerUser(@RequestBody SignUpRequestDto signUpRequestDto){
      return userService.registerUser(signUpRequestDto);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequestDto loginRequestDto){
      return  userService.loginUser(loginRequestDto);
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<Void>logoutUser(@RequestParam(value = "id") Long userid ,
                                          @RequestBody LogoutRequestDto logoutRequestDto){
         return userService.logoutUser(logoutRequestDto.getSessionId() , userid );
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id") Long userId){
        return userService.deleteUser(userId);
    }

}
