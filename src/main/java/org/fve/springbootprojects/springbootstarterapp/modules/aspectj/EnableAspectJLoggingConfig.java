package org.fve.springbootprojects.springbootstarterapp.modules.aspectj;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ConditionalOnProperty(name = "app.config.aspectjlogging.enabled", matchIfMissing = true)
@Configuration
@Profile("!test")
@EnableAspectJAutoProxy
public class EnableAspectJLoggingConfig {
}
