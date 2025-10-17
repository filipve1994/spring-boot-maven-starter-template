package org.fve.springbootprojects.springbootstarterapp.modules.notification.mail;

import org.fve.springbootprojects.springbootstarterapp.security.models.User;

public interface MailSenderService {
    void sendUserPasswordReset(User user);

    void sendUserEmailVerification(User user);
}
