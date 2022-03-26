package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.LoginForm;
import stander.stander.model.Member;
import stander.stander.model.MemberForm;
import stander.stander.model.Test;
import stander.stander.repository.JpaRepository;
import stander.stander.service.MemberService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    public String home(HttpServletRequest request, Model model)  {

        HttpSession session = request.getSession();
        if(session == null) {
            return "menu/home";
        }
        Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        if(member == null) {
            return "menu/home";
        }

        model.addAttribute("member", member);

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
    public String check_login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if(bindingResult.hasErrors()) {
            return "login/login";
        }
        Member member = memberService.login(loginForm);
        if(member != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER, member);
            session.setMaxInactiveInterval(3);
            return "redirect:/";
        }
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
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

    @GetMapping("/login/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";

    }


}
