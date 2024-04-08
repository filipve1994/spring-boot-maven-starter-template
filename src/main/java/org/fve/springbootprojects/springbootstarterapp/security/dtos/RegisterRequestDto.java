package org.fve.springbootprojects.springbootstarterapp.security.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @Schema(example = "Filip VE")
    @NotNull
    private String name;

    @Schema(example = "filipve")
    @NotNull
    @Size(min = 3)
    private String username;

    @Schema(example = "filipve@gmail.com")
    @Email
    private String email;

    @Schema(example = "Pass@123")
    @NotEmpty
    @Size(min = 8)
    private String password;
}