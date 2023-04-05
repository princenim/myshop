package shop.shop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.shop.model.JsonResult;

/**
 * @author hazel
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "heyyyy");
        //tempaltes에 hello.html로 연결
        return "hello";
    }

    @GetMapping("test")
    public JsonResult test(){
        return JsonResult.ok("json test");
    }

}
