package com.localservicereviewplatform.UserServiceManagment.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends  BaseModel{

    private String sessionId;
    @ManyToOne
    private User user;
    private SessionStatus sessionStatus;
}
