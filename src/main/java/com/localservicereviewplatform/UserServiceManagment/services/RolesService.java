package com.localservicereviewplatform.UserServiceManagment.services;

import com.localservicereviewplatform.UserServiceManagment.dtos.UserDto;
import com.localservicereviewplatform.UserServiceManagment.models.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RolesService {

    public ResponseEntity<Role> createRoles();

    public ResponseEntity<Void>deleteRoles();

    public ResponseEntity<UserDto> assignRoles();

    public ResponseEntity<List<Role>> fetchUserRoles();
}
