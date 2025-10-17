package org.fve.springbootprojects.springbootstarterapp.modules.user.entity.specification.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fve.springbootprojects.springbootstarterapp.security.enums.RoleEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class UserCriteria {
//    private List<RoleEnum> roles;
    private List<String> roles;

    private Boolean isAvatar;

    private LocalDateTime createdAtStart;

    private LocalDateTime createdAtEnd;

    private Boolean isEmailActivated;

    private Boolean isBlocked;

    private String q;
}