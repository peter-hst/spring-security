package me.togo.security.controller;

import me.togo.security.vo.User;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HomeController {

    private final AtomicLong count = new AtomicLong(0);

    @GetMapping("/")
    public String index() {
        return "Welcome to Spring Security Demo " + count.incrementAndGet();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, The World!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //调用前权限检查
//    @PostAuthorize() //调用后权限检查
//    @PreFilter() 用于过滤集合
//    @PostFilter() 用于过滤集合
    @GetMapping("/roleAuth")
    public String roleAuth() {
        return "admin auth by role page " + count.incrementAndGet();
    }

    @PreAuthorize("#id < 10 and principal.username.equals(#username) and user.username.equals('abc')")
//    @PostAuthorize("returnObject%2==0") // returnObject获取方法返回值，他是在方法执行完成后执行的注解
    @GetMapping("/test")
    public Integer test(Integer id, String username, User user) {
        return id;
    }

    @PreFilter("filterObject%2==0") //对集合类型的传入参数进行过滤
    @PostFilter("filterObject%4==0") //对返回的集合值进行过滤
    @GetMapping("/test2")
    public List<Integer> test2(List<Integer> idList) {
        return idList;
    }
}
