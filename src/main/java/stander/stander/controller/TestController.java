package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stander.stander.model.Member;
import stander.stander.model.Test;
import stander.stander.repository.JpaRepository;

@Controller
public class TestController {

    private JpaRepository jpaRepository;

    public TestController(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @GetMapping("/test")
    public String layout() {
        return "layout/layout";
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "name", required=false, defaultValue = "home") String name, Model model)  {
        Test member = new Test();
        member.setName("woojin");
        member.setAge("25");
        jpaRepository.save(member);
        model.addAttribute("name", name);

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
