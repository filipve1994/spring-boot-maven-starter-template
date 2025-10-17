package org.fve.springbootprojects.springbootstarterapp.modules.auth.repository;

import org.fve.springbootprojects.springbootstarterapp.modules.auth.entities.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, UUID> {
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    //    Optional<EmailVerificationToken> findByUserId(UUID userId);
    Optional<EmailVerificationToken> findByUserId(long userId);

    Optional<EmailVerificationToken> findByToken(String token);

    @Modifying
    @Query("DELETE FROM EmailVerificationToken rt WHERE rt.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
//    void deleteByUserId(@Param("userId") UUID userId);
}