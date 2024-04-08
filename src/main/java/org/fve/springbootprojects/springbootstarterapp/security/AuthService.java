package org.fve.springbootprojects.springbootstarterapp.security;

import org.fve.springbootprojects.springbootstarterapp.security.dtos.RegisterRequestDto;

public interface AuthService {

    String register(RegisterRequestDto registerRequestDto);
}
