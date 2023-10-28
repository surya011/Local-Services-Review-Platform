package com.localservicereviewplatform.UserServiceManagment.dtos;

import com.localservicereviewplatform.UserServiceManagment.models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDto {

    private String sessionId;
    private SessionStatus sessionStatus;

}
