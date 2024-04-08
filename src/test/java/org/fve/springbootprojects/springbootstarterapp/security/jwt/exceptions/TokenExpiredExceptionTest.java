package org.fve.springbootprojects.springbootstarterapp.security.jwt.exceptions;

import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.AppExceptionHandler;
import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.ErrorResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.function.*;
import org.mockito.junit.jupiter.*;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@Tag("unit")
@DisplayName("Unit test for ExpectationException")
class TokenExpiredExceptionTest {
    @Test
    @DisplayName("Test ExpectationException")
    void testHandleExpectationException() {
        // Given
        TokenExpiredException exception = Instancio.create(TokenExpiredException.class);
        AppExceptionHandler exceptionHandler = Instancio.create(AppExceptionHandler.class);
        // When
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleTokenExpiredRequestException(exception);
        // Then
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}