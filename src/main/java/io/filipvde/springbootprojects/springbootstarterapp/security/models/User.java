package org.fve.springbootprojects.springbootstarterapp.security.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fve.springbootprojects.springbootstarterapp.entity.AbstractBaseEntity;
import org.fve.springbootprojects.springbootstarterapp.modules.auth.entities.EmailVerificationToken;
import org.fve.springbootprojects.springbootstarterapp.modules.auth.entities.PasswordResetToken;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "users", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"email"}, name = "uk_users_email")
//}, indexes = {
//        @Index(columnList = "name", name = "idx_users_name"),
//        @Index(columnList = "last_name", name = "idx_users_last_name")
//})
@Table(name = "users")
//public class User {
public class User extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = true, unique = false, length = 50)
    private String name;

    @Column(name = "name", nullable = true, unique = false, length = 50)
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinTable(name = "user_roles",
//            joinColumns = @JoinColumn(
//                    name = "user_id",
//                    foreignKey = @ForeignKey(
//                            name = "fk_user_roles_user_id",
//                            foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE"
//                    ),
//                    nullable = false
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id",
//                    foreignKey = @ForeignKey(
//                            name = "fk_user_roles_role_id",
//                            foreignKeyDefinition = "FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE"
//                    ),
//                    nullable = false
//            ),
//            uniqueConstraints = {
//                    @UniqueConstraint(
//                            columnNames = {"user_id", "role_id"},
//                            name = "uk_user_roles_user_id_role_id"
//                    )
//            }
//    )
//    @Builder.Default
//    private List<Role> roles = new ArrayList<>();

//    @Column(name = "avatar", columnDefinition = "text")
//    private String avatar;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmailVerificationToken emailVerificationToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private PasswordResetToken passwordResetToken;

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;

    @Column(name = "blocked_at")
    private LocalDateTime blockedAt;

    /**
     * Get full name of user.
     *
     * @return String
     */
    @JsonGetter(value = "fullName")
    public String getFullName() {
        return this.lastName + " " + this.name;
    }
}