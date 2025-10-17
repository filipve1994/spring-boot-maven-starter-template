package org.fve.springbootprojects.springbootstarterapp.modules.internationalization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class InternationalizationConfig {

    /**
     * Locale resolver bean.
     *
     * @param defaultLocale String
     * @return LocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver(@Value("${app.default-locale:en}") final String defaultLocale,
                                         @Value("${app.default-timezone:UTC}") final String defaultTimezone) {
        AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
        localResolver.setDefaultLocale(new Locale.Builder().setLanguage(defaultLocale).build());
        TimeZone.setDefault(TimeZone.getTimeZone(defaultTimezone));

        return localResolver;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(MessageSource messageSource) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setValidationMessageSource(messageSource);
        return validatorFactoryBean;
    }
}
