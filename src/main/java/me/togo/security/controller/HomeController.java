package me.togo.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HomeController {

    private final AtomicLong count = new AtomicLong(0);

    @GetMapping("/")
    public String index() {
        return "Welcome to Spring Security Demo " + count.incrementAndGet();
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello, The World!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/roleAuth")
    public String roleAuth(){
        return "admin auth by role page "+ count.incrementAndGet();
    }
}
