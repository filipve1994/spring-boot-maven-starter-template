package org.fve.springbootprojects.springbootstarterapp.event;

import lombok.Getter;
import org.fve.springbootprojects.springbootstarterapp.security.models.User;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserPasswordResetSendEvent extends ApplicationEvent {
    private final User user;

    public UserPasswordResetSendEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}