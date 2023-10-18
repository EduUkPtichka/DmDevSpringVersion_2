package org.example.springframework.example.database.repository;

import org.example.springframework.example.database.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(@Qualifier("pool2") ConnectionPool pool2) {
        connectionPool = pool2;
    }
}
