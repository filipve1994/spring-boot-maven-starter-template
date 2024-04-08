package org.fve.springbootprojects.springbootstarterapp.modules.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConditionalOnProperty(name = "app.config.jpa.auditing.enabled", matchIfMissing = true)
@Configuration
@Profile("!test")
@EnableJpaAuditing
public class EnableJpaAuditingConfig {
}
