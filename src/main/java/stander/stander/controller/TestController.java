package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping
    @ResponseBody
    public Map<String, String> test_basic() {
        Map<String, String> map = new HashMap<>();
        map.put("hello", "woojin");
        return map;
    }
}
