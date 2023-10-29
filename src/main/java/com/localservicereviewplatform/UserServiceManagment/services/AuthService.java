package com.localservicereviewplatform.UserServiceManagment.services;

import com.localservicereviewplatform.UserServiceManagment.dtos.LoginRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.SignUpRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import com.localservicereviewplatform.UserServiceManagment.models.User;
import com.localservicereviewplatform.UserServiceManagment.repositories.SessionRepository;
import com.localservicereviewplatform.UserServiceManagment.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("UserServiceImplementation")
public class AuthService implements UserService{

    private  final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ResponseEntity<UserDto> registerUser(SignUpRequestDto signUpRequestDto) {
         Optional<User> userOptional = userRepository.findByEmail(signUpRequestDto.getEmail());
         if (!userOptional.isEmpty())
             return new ResponseEntity<UserDto>(mappingforUser(userOptional.get()) , HttpStatus.OK );

       User user = new User();
       user.setName(signUpRequestDto.getName());
       user.setEmail(signUpRequestDto.getEmail());
       user.setPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
       User savedUser = userRepository.save(user);
       return new ResponseEntity<UserDto>(mappingforUser(savedUser) , HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDto> loginUser(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        Optional<User> useroptional = userRepository.findById(id);
        if(useroptional.isEmpty())
            return "User does not Exist";
    userRepository.delete(useroptional.get());

    return "User Deleted Successfully.";
    }


    @Override
    public String resetPassword(String email, String newPassword) {
        return null;
    }

    public UserDto mappingforUser(User user){
        UserDto userdto = new UserDto();
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        return userdto;
    }

}
