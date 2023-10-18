package org.example.springframework.listener;

import org.example.springframework.listener.entity.EntityEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class EntityListener {

    @Order(10)
    @EventListener(condition = "#root.args[0].acceptType.name()== READ}")
    public void acceptEntityRead(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }

}
