package org.fve.springbootprojects.springbootstarterapp.modules.user.dtos.request;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class UpdateProfileRequest extends AbstractBaseUpdateUserRequest {
}