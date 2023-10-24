package com.example.springsource.jianzhi.domain;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 描述：FactoryBean
 */
@Component
public class User implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Car();
    }
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}
