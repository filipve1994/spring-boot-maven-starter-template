package org.fve.springbootprojects.springbootstarterapp.modules.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.swagger")
public class SwaggerProperties {

    private String appName;

    private String appDescription;

    private String appVersion;

    private String appLicense;

    private String appLicenseUrl;

    private String contactName;

    private String contactUrl;

    private String contactMail;

}
