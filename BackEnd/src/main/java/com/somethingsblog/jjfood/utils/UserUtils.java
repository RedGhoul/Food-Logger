package com.somethingsblog.jjfood.utils;

import com.somethingsblog.jjfood.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserUtils {
    public static Optional<UserDetails> getCurrentUserDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)auth.getPrincipal();
        return Optional.of(userDetails);
    }
}
