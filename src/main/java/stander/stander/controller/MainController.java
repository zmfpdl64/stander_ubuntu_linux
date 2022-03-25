package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Member;
import stander.stander.model.MemberForm;
import stander.stander.model.Test;
import stander.stander.repository.JpaRepository;
import stander.stander.service.MemberService;

@Controller
public class MainController {

    private MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/test")
    public String layout() {
        return "layout/layout";
    }
//    @GetMapping("/test2")
//    public String test() {
//        return "test";
//    }


    @GetMapping("/")
    public String home(@RequestParam(value = "name", required=false, defaultValue = "home") String name, Model model)  {
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

    @GetMapping("/login")
    public String post_login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String check_login() {
        return "login/login";
    }


    @GetMapping("/login/join")
    public String post_join() {
        return "login/join";
    }

    @PostMapping("/login/join")
    public String create_join(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setUsername(memberForm.getUsername());
        member.setPassword(memberForm.getPassword());
        member.setPhonenum(memberForm.getPhonenum());
        member.setPersonnum_front(memberForm.getPersonnum_front());
        member.setPersonnum_back(memberForm.getPersonnum_back());
        memberService.join(member);
        return "redirect:/";
    }


}
