package org.fve.springbootprojects.springbootstarterapp.event;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.notification.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppEventListener {

    @Autowired
    private MailSenderService mailSenderService;

    @EventListener(UserEmailVerificationSendEvent.class)
    public void onUserEmailVerificationSendEvent(UserEmailVerificationSendEvent event) {
        log.info("[User e-mail verification mail send event listener] {} - {}",
                event.getUser().getEmail(), event.getUser().getId());
        mailSenderService.sendUserEmailVerification(event.getUser());
    }

    @EventListener(UserPasswordResetSendEvent.class)
    public void onUserPasswordResetSendEvent(UserPasswordResetSendEvent event) {
        log.info("[User password reset mail send event listener] {} - {}",
                event.getUser().getEmail(), event.getUser().getId());
        mailSenderService.sendUserPasswordReset(event.getUser());
    }
}
