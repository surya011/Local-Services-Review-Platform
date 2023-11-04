package com.localservicereviewplatform.UserServiceManagment.services;

import com.localservicereviewplatform.UserServiceManagment.dtos.LoginRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.SignUpRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<UserDto> registerUser(SignUpRequestDto signUpRequestDto);

    public ResponseEntity<UserDto> loginUser(LoginRequestDto loginRequestDto);

    public ResponseEntity<Void> logoutUser(String sessionId, Long userId);

    public String deleteUser(Long userid);





    public String resetPassword(String email , String newPassword);

}
