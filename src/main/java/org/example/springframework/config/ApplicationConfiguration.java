package org.example.springframework.config;

import org.example.springframework.example.database.pool.ConnectionPool;
import org.example.springframework.example.database.repository.CrudRepository;
import org.example.springframework.example.database.repository.UserRepository;
import org.example.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

import java.awt.*;

@Import(WebConfiguration.class)
@ImportResource("classpath:application.xml")
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example.springframework",
        useDefaultFilters = false,
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = Component.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
        })
public class ApplicationConfiguration {

    @Bean
    public UserRepository userRepository3() {
        var connectionPool1 = pool3();
        var connectionPool2 = pool3();
        var connectionPool3 = pool3();
        return new UserRepository(pool3());
    }


    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean("pool3")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }


    @Profile("prod | web")
    @Bean
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }


}
