package com.example.my_app.service;

import com.example.my_app.config.MyUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityContextService {

    public UUID getCurredUserId(){
        return ((MyUserDetails)SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUserId();
    }
}
