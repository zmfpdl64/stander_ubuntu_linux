package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String layout() {
        return "layout/layout";
    }

    @GetMapping("/home")
    public String home() {
        return "menu/home";
    }

    @GetMapping("/about_cafe")
    public String about_cafe() {
        return "menu/about_cafe";
    }

    @GetMapping("/reserve")
    public String reserve() {
        return "menu/reserve";
    }

    @GetMapping("/map")
    public String map() {
        return "menu/map";
    }


}
