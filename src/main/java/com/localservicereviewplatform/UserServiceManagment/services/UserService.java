package com.localservicereviewplatform.UserServiceManagment.services;

import com.localservicereviewplatform.UserServiceManagment.dtos.LoginRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.SignUpRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import com.localservicereviewplatform.UserServiceManagment.models.Role;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<UserDto> registerUser(SignUpRequestDto signUpRequestDto);

    public ResponseEntity<UserDto> loginUser(LoginRequestDto loginRequestDto);

    public ResponseEntity<Void> logoutUser();

    public String deleteUser(Long userid);

    public String resetPassword(String email , String newPassword);

}
