package com.example.springsource;

import com.example.springsource.jianzhi.config.MainConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
class SpringSourceApplicationTests {
    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Object car = annotationConfigApplicationContext.getBean("car");
        System.out.println(car);
    }
    @Test
    void factoryBeanTest() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Object car = annotationConfigApplicationContext.getBean("user");
        System.out.println(car);
    }
}
