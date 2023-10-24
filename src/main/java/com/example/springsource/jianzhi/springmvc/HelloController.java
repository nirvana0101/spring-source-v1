package com.example.springsource.jianzhi.springmvc;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：TODO
 */
@RestController
public class HelloController {
    @GetMapping("hello")
    public String main_(){
        return "hello world";
    }
}
