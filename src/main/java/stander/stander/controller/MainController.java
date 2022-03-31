package stander.stander.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Entity.Sit;
import stander.stander.model.Form.LoginForm;
import stander.stander.model.Entity.Member;
import stander.stander.model.Form.MemberForm;
import stander.stander.service.MemberService;
import stander.stander.service.SitService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    private MemberService memberService;
    private SitService sitService;

    public MainController(MemberService memberService, SitService sitService) {
        this.memberService = memberService;
        this.sitService = sitService;
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


        return "menu/home";
    }

    @GetMapping("/about_cafe")
    public String about_cafe() {
        return "menu/about_cafe";
    }

    @GetMapping("/reserve")
    public String reserve(Model model) {

        List<Sit> sits = sitService.findAll();
        model.addAttribute("sits", sits);
        return "reserve/reserve";
    }

    @GetMapping("/reserve/price")
    public String price(@RequestParam("num") String num, Model model) {
        Member member = memberService.findById(7L);
        Long id = Long.parseLong(num);
        sitService.use(member, id);
        model.addAttribute("num", num);
        model.addAttribute("member", member);
        return "reserve/sit";
    }
    @GetMapping("/reserve/clear")
    public String clear(Model model) {
        sitService.clear();
        List<Sit> sits = sitService.findAll();
        model.addAttribute("sits", sits);
        return "reserve/reserve";
    }

//    @GetMapping("/reserve/set_sit")
//    public void reserve() {
//        for(int i = 0; i < 30; i++) {
//            Sit sit = new Sit();
//            sitService.use(sit);
//        }
//    } //db에 좌석 생성

    @GetMapping("/map")
    public String map() {
        return "menu/map";
    }

    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if(session == null) {
            return "menu/home";
        }
        Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        if(member == null) {
            return "menu/home";
        }

        model.addAttribute("member", member);

        return "menu/mypage";
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

            return "redirect:/mypage";
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
