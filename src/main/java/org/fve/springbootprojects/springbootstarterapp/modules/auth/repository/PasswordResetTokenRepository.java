package org.fve.springbootprojects.springbootstarterapp.modules.auth.repository;

import org.fve.springbootprojects.springbootstarterapp.modules.auth.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    //    Optional<PasswordResetToken> findByUserId(UUID userId);
    Optional<PasswordResetToken> findByUserId(Long userId);

    Optional<PasswordResetToken> findByToken(String token);

    @Modifying
    @Query("DELETE FROM PasswordResetToken rt WHERE rt.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
//    void deleteByUserId(@Param("userId") UUID userId);
}