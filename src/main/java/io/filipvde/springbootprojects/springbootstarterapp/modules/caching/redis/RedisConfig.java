package org.fve.springbootprojects.springbootstarterapp.modules.caching.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.database}")
    private String database;

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private String port;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.timeout}")
    private String timeout;

//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setDatabase(Integer.parseInt(database));
//        config.setHostName(host);
//        config.setPort(Integer.parseInt(port));
//        config.setPassword(password);
//
//        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
//                .commandTimeout(Duration.ofMillis(Long.parseLong(timeout)))
//                .build();
//
//        return new LettuceConnectionFactory(config, lettuceClientConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        return template;
//    }

}
