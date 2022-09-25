package com.somethingsblog.jjfood.controller;

import com.somethingsblog.jjfood.utils.UserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Value("${health.message}")
    private String healthMessage;

    @GetMapping("/")
    public String helloWorld(){
        Optional<UserDetails> userDetails = UserUtils.getCurrentUserDetails();
        if(userDetails.isPresent()){
            return "Services are up " + userDetails.get().getUsername();
        }
        return healthMessage;
    }
}
