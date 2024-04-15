package org.fve.springbootprojects.springbootstarterapp.modules.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.auth.entities.EmailVerificationToken;
import org.fve.springbootprojects.springbootstarterapp.modules.auth.repository.EmailVerificationTokenRepository;
import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.BadRequestException;
import org.fve.springbootprojects.springbootstarterapp.modules.globalapp.exception.NotFoundException;
import org.fve.springbootprojects.springbootstarterapp.modules.internationalization.service.MessageSourceService;
import org.fve.springbootprojects.springbootstarterapp.security.models.User;
import org.fve.springbootprojects.springbootstarterapp.utils.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.fve.springbootprojects.springbootstarterapp.security.SecurityConstants.EMAIL_VERIFICATION_TOKEN_LENGTH;

@Service
public class EmailVerificationTokenService {

    private static final Logger log = LoggerFactory.getLogger(EmailVerificationTokenService.class);

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    private final MessageSourceService messageSourceService;

    private final Long expiresIn;

    /**
     * Email verification token constructor.
     *
     * @param emailVerificationTokenRepository EmailVerificationTokenRepository
     * @param messageSourceService             MessageSourceService
     * @param expiresIn                        Long
     */
    public EmailVerificationTokenService(
            EmailVerificationTokenRepository emailVerificationTokenRepository,
            MessageSourceService messageSourceService,
            @Value("${app.registration.email.token.expires-in}") Long expiresIn
    ) {
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
        this.messageSourceService = messageSourceService;
        this.expiresIn = expiresIn;
    }

    /**
     * Is e-mail verification token expired?
     *
     * @param token EmailVerificationToken
     * @return boolean
     */
    public boolean isEmailVerificationTokenExpired(EmailVerificationToken token) {
        return token.getExpirationDate().before(new Date());
    }

    /**
     * Create email verification token from user.
     *
     * @param user User
     * @return EmailVerificationToken
     */
    public EmailVerificationToken create(User user) {
        String newToken = new RandomStringGenerator(EMAIL_VERIFICATION_TOKEN_LENGTH).next();
        Date expirationDate = Date.from(Instant.now().plusSeconds(expiresIn));
        Optional<EmailVerificationToken> oldToken = emailVerificationTokenRepository.findByUserId(user.getId());
        EmailVerificationToken emailVerificationToken;

        if (oldToken.isPresent()) {
            emailVerificationToken = oldToken.get();
            emailVerificationToken.setToken(newToken);
            emailVerificationToken.setExpirationDate(expirationDate);
        } else {
            emailVerificationToken = EmailVerificationToken.builder()
                    .user(user)
                    .token(newToken)
                    .expirationDate(Date.from(Instant.now().plusSeconds(expiresIn)))
                    .build();
        }

        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    /**
     * Get email verification token by token.
     *
     * @param token String
     * @return User
     */
    public User getUserByToken(String token) {
        EmailVerificationToken emailVerificationToken = emailVerificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException(messageSourceService.get("not_found_with_param",
                        new String[]{messageSourceService.get("token")})));

        if (isEmailVerificationTokenExpired(emailVerificationToken)) {
            throw new BadRequestException(messageSourceService.get("expired_with_param",
                    new String[]{messageSourceService.get("token")}));
        }

        return emailVerificationToken.getUser();
    }

    /**
     * Delete email verification token by user ID.
     *
     * @param userId UUID
     */
//    public void deleteByUserId(UUID userId) {
    public void deleteByUserId(Long userId) {
        emailVerificationTokenRepository.deleteByUserId(userId);
    }
}