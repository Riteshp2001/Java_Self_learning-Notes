package com.ritesh.spring_framework;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringFrameworkController {
    @RequestMapping
    public String index() {
        return "Hello World!";
    }
}
