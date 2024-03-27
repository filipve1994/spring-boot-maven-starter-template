package org.fve.springbootprojects.springbootstarterapp.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;

@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    @Profile("!local")
    public OpenAPI openAPI() {
        final OpenAPI openAPI = new OpenAPI();

        final Info apiInformation = getApiInformation();
        openAPI.setInfo(apiInformation);

        final Components components = new Components();
        openAPI.setComponents(components);

        final String schemeNameBearerAuth = "bearerAuth";
        components.addSecuritySchemes(
                schemeNameBearerAuth,
                new SecurityScheme()
                        .name(schemeNameBearerAuth)
                        .type(HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
        );

        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeNameBearerAuth));

//        final String schemeNameBasicAuth = "basicAuth";
//        components.addSecuritySchemes(
//                schemeNameBasicAuth,
//                new SecurityScheme()
//                        .name(schemeNameBasicAuth)
//                        .type(HTTP)
//                        .scheme("basic")
//                        .in(SecurityScheme.In.HEADER)
//        );


//        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeNameBasicAuth));

        return openAPI;
    }

    private Info getApiInformation() {

        final License license = new License();
        license.setName(swaggerProperties.getAppLicense());
        license.setUrl(swaggerProperties.getAppLicenseUrl());

        final Contact contact = new Contact();
        contact.setName(swaggerProperties.getContactName());
        contact.setUrl(swaggerProperties.getContactUrl());
        contact.setEmail(swaggerProperties.getContactMail());


        final Info info = new Info();
        info.setTitle(swaggerProperties.getAppName());
        info.setVersion(swaggerProperties.getAppVersion());
        info.setDescription(swaggerProperties.getAppDescription());
//        info.setDescription("""
//                        <p>This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.</p>
//                        <br>
//                        <p><b>Test credentials:</b><br>
//                        - einstein@gmail.com / password<br>
//                        - newton@gmail.com / password<br>
//                        - user1@gmail.com / user1@123<br>
//                        - user2@gmail.com / user2@123</p>
//                        """);
        info.setLicense(license);
        info.setContact(contact);

        return info;
    }

    @Bean
    @Profile("local")
    public OpenAPI openAPILocal() {

        final Info apiInformation = getApiInformationLocally();
        final Components components = new Components();

        final String schemeName = "bearerAuth";
        components.addSecuritySchemes(
                schemeName,
                new SecurityScheme()
                        .name(schemeName)
                        .type(HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
        );

        final String schemeNameBasicAuth = "basicAuth";
        components.addSecuritySchemes(
                schemeNameBasicAuth,
                new SecurityScheme()
                        .name(schemeNameBasicAuth)
                        .type(HTTP)
                        .scheme("basic")
                        .in(SecurityScheme.In.HEADER)
        );

        final OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(apiInformation);
        openAPI.setComponents(components);
        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeName));
        openAPI.addSecurityItem(new SecurityRequirement().addList(schemeNameBasicAuth));

        return openAPI;
    }

    private Info getApiInformationLocally() {
        final License license = new License();
        license.setName(swaggerProperties.getAppLicense());
        license.setUrl(swaggerProperties.getAppLicenseUrl());

        final Contact contact = new Contact();
        contact.setName(swaggerProperties.getContactName());
        contact.setUrl(swaggerProperties.getContactUrl());
        contact.setEmail(swaggerProperties.getContactMail());


        final Info info = new Info();
        info.setTitle(swaggerProperties.getAppName());
        info.setVersion(swaggerProperties.getAppVersion());
//        info.setDescription(swaggerProperties.getAppDescription());
        info.setDescription("""
                <p>This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.</p>
                <br>
                <p><b>Test credentials:</b><br>
                - einstein@gmail.com / password<br>
                - newton@gmail.com / password<br>
                - user1@gmail.com / user1@123<br>
                - user2@gmail.com / user2@123</p>
                """);
        info.setLicense(license);
        info.setContact(contact);

        return info;
    }
}
