package org.fve.springbootprojects.springbootstarterapp.security.jwt;

import org.fve.springbootprojects.springbootstarterapp.security.dtos.LoginRequestDto;
import org.fve.springbootprojects.springbootstarterapp.security.dtos.RegisterRequestDto;

public interface JwtAuthService {
    String register(RegisterRequestDto registerDto);

    JwtAuthResponse login(LoginRequestDto loginDto);
}