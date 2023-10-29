package com.localservicereviewplatform.UserServiceManagment.dtos;

import com.localservicereviewplatform.UserServiceManagment.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long userid;

    private String name;
    private String email;
    private String password;

    private Set<Role> roles = new HashSet<>();

}
