package org.fve.springbootprojects.springbootstarterapp.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.jwt")
@Getter
@Setter
public class JwtProperties {

    private String issuer;

    private String secretKey;

    private long expirationMilliseconds;

    private int expirationMinute;
}
