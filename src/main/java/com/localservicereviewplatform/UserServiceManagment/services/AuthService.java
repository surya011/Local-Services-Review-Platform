package com.localservicereviewplatform.UserServiceManagment.services;

import com.localservicereviewplatform.UserServiceManagment.dtos.LoginRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.LogoutRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.SignUpRequestDto;
import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import com.localservicereviewplatform.UserServiceManagment.models.Session;
import com.localservicereviewplatform.UserServiceManagment.models.SessionStatus;
import com.localservicereviewplatform.UserServiceManagment.models.User;
import com.localservicereviewplatform.UserServiceManagment.repositories.SessionRepository;
import com.localservicereviewplatform.UserServiceManagment.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.*;


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

        Optional<User> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());
        if (userOptional.isEmpty())
            throw  new UsernameNotFoundException("User Does exist with email");

        User user = userOptional.get();
        UserDto userDto = mappingforUser(user);
        Optional<Session> sessionOptional = sessionRepository.findByUser_Id(user.getId());
        if (!sessionOptional.isEmpty()){
            return  new ResponseEntity<>(userDto ,HttpStatus.OK);
        }


        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword()))
           throw  new RuntimeException("Wrong  Username password ");



        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();
        HashMap<String , String> jwtPayload = new HashMap<String , String>();
        jwtPayload.put("email" , loginRequestDto.getEmail());
        jwtPayload.put("createdDate" , LocalDateTime.now().toString());

        String token = Jwts.builder().claims(jwtPayload).signWith(key, alg).compact();

       Session session =  new Session();
       session.setSessionStatus(SessionStatus.ACTIVE);
       session.setSessionId(token);
       session.setUser(user);
       sessionRepository.save(session);

        MultiValueMap<Object, Object> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.set(HttpHeaders.SET_COOKIE , "authtoken:" + token);

        ResponseEntity<UserDto> response = new ResponseEntity(userDto, headers, HttpStatus.OK);

        return response;
    }

    @Override
    public ResponseEntity<Void> logoutUser(String sessionId , Long userid ) {
        Optional<Session> sessionOptional = sessionRepository.findBySessionIdAndUser_Id(sessionId , userid);
        if(sessionOptional.isEmpty())
           throw new RuntimeException("User is already logged out");

        Session userSession = sessionOptional.get();
        userSession.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(userSession);
        return ResponseEntity.ok().build();
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
