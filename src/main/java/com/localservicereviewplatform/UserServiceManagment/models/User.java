package com.localservicereviewplatform.UserServiceManagment.models;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel{


    @Nonnull
    private String name;
    @Nonnull
    private String email;
    @Nonnull
    private String password;

    @ManyToMany
    private Set<Role> roles= new HashSet<>();
}
