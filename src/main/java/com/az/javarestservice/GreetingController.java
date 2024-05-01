package com.az.javarestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s! from Git Actions";
    private final AtomicLong counter = new AtomicLong();


    @Autowired
    private Environment environment;
//    @Value("${connection:absent}")
//    private String connection ;
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    @GetMapping("/db")
    public Greeting getEnv(@RequestParam(value = "name", defaultValue = "World") String name) {
        String value = environment.getProperty("SQLAZURECONNSTR_SQLConnectionSTRING");
        return new Greeting(counter.incrementAndGet(), value);
//                String.format(template, name));
    }
}
 record  Greeting(long id, String content) {}