package stander.stander.controller;


import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RestController {
    @GetMapping("/test1")
    @ResponseBody
    public Map<String, String> test1() {
        JSONObject obj = new JSONObject();
        Map<String, String> map = new HashMap<>();

        map.put("username", "woojin");
        map.put("password", "1234");
        map.put("phonenum", "01021106737");
        map.put("personnum", "xxxxxx");

        obj.put("username", "woojin");
        obj.put("password", "1234");
        obj.put("phonenum", "01021106737");
        obj.put("personnum", "xxxxxx");
        System.out.println(obj);
        return map;
    }
}
