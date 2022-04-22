package stander.stander.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stander.stander.model.Entity.Member;
import stander.stander.model.Form.LoginForm;
import stander.stander.model.Form.MemberForm;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private SeatService seatService;


    @GetMapping
    public String post_login() {
        return "login/login";
    }

    @PostMapping
    public String check_login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response
            , Model model) {
        if(bindingResult.hasErrors()) {
            return "login/login";
        }
        Member member = memberService.login(loginForm);
        if(member != null) {
            HttpSession session = request.getSession();
            session.setAttribute(SessionConstants.LOGIN_MEMBER, member);

            return "redirect:/mypage";
        }
        bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다");
        model.addAttribute("member", loginForm);
        return "login/login";
    }


    @GetMapping("/join")
    public String post_join() {
        return "login/join";
    }

    @PostMapping("/join")
    public String create_join(@ModelAttribute MemberForm memberForm, Model model) {
        Member member = new Member();
        member.setUsername(memberForm.getUsername());
        member.setPassword(memberForm.getPassword());
        member.setAge(memberForm.getAge());
        member.setGender(memberForm.getGender());
        memberService.join(member);
        model.addAttribute("member", memberForm);
//        return "login/join";
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }


}
