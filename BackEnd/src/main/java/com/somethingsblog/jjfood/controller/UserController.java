package com.somethingsblog.jjfood.controller;

import com.somethingsblog.jjfood.entity.AppRole;
import com.somethingsblog.jjfood.jwt.JwtResponse;
import com.somethingsblog.jjfood.jwt.JwtUtils;
import com.somethingsblog.jjfood.jwt.LoginRequest;
import com.somethingsblog.jjfood.jwt.SignupRequest;
import com.somethingsblog.jjfood.entity.AppUser;
import com.somethingsblog.jjfood.repo.AppRoleRepo;
import com.somethingsblog.jjfood.repo.AppUserRepo;
import com.somethingsblog.jjfood.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {
    final
    AuthenticationManager authenticationManager;
    final
    AppUserRepo userRepository;
    final
    AppRoleRepo roleRepository;
    final
    PasswordEncoder encoder;
    final
    JwtUtils jwtUtils;

    @Value("${login.adminEnabled}")
    boolean everUserIsAdmin;

    public UserController(AuthenticationManager authenticationManager, AppUserRepo userRepository,
                          AppRoleRepo roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/SignIn")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(everUserIsAdmin){
            AppUser curAppUser = userRepository.findAppUserByUsername(loginRequest.getUsername());
            AppRole curAppRole = roleRepository.findAppRoleByName("ADMIN");
            if(!curAppUser.getRoles().contains(curAppRole)){
                curAppUser.getRoles().add(curAppRole);
            }
            userRepository.save(curAppUser);
        }

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }
        // Create new user's account
        AppUser user = new AppUser(
                signUpRequest.getUserName(),
                signUpRequest.getUserName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), null);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

}
