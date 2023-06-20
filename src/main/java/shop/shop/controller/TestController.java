package shop.shop.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.shop.service.TestService;

/**
 * @author hazel
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    public final TestService testService;

    @GetMapping("/test2")
    public String Test() {
        System.out.println(Thread.currentThread().getName());
        testService.testAsync();
        return "hi";
    }
}
