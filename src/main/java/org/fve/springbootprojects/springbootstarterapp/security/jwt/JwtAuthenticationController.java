package org.fve.springbootprojects.springbootstarterapp.security.jwt;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.fve.springbootprojects.springbootstarterapp.security.dtos.LoginRequestDto;
import org.fve.springbootprojects.springbootstarterapp.security.dtos.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
@Tag(name = "JwtAuthenticationController", description = "authentication with JWT tokens flow")
public class JwtAuthenticationController {

    @Autowired
    private JwtAuthService jwtAuthService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerDto){
        String response = jwtAuthService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequestDto loginDto){
        JwtAuthResponse jwtAuthResponse = jwtAuthService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
