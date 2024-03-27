package org.fve.springbootprojects.springbootstarterapp.config;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.util.TimeZone;

@Configuration
public class AppConfig {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Brussels"));
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
