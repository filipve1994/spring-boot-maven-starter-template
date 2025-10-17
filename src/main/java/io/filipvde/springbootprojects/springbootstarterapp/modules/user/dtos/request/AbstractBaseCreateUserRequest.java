package org.fve.springbootprojects.springbootstarterapp.modules.user.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.fve.springbootprojects.springbootstarterapp.validation.FieldMatch;
import org.fve.springbootprojects.springbootstarterapp.validation.Password;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@FieldMatch(first = "password", second = "passwordConfirm", message = "{password_mismatch}")
public abstract class AbstractBaseCreateUserRequest {

    @NotBlank(message = "{not_blank}")
    @Email(message = "{invalid_email}")
    @Size(max = 100, message = "{max_length}")
    @Schema(
            name = "email",
            description = "Email of the user",
            type = "String",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "mail@example.com"
    )
    private String email;

    @NotBlank(message = "{not_blank}")
    @Password(message = "{invalid_password}")
    @Schema(
            name = "password",
            description = "Password of the user",
            type = "String",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "P@sswd123."
    )
    private String password;

    @NotBlank(message = "{not_blank}")
    @Schema(
            name = "passwordConfirm",
            description = "Password for confirmation",
            type = "String",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "P@sswd123."
    )
    private String passwordConfirm;

    @NotBlank(message = "{not_blank}")
    @Size(max = 50, message = "{max_length}")
    @Schema(
            name = "name",
            description = "Name of the user",
            type = "String",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "John"
    )
    private String name;

    @NotBlank(message = "{not_blank}")
    @Size(max = 50, message = "{max_length}")
    @Schema(
            name = "lastName",
            description = "Lastname of the user",
            type = "String",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "DOE"
    )
    private String lastName;
}