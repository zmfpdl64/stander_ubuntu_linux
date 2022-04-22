package stander.stander.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Entity.Member;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MainController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private SeatService sitService;

    @Value("${file.dir}")
    private String fileDir;

//    public MainController(MemberService memberService, SitService sitService) {
//        this.memberService = memberService;
//        this.sitService = sitService;
//    }


//    @GetMapping("/test")
//    public String layout() {
//        return "layout/layout";
//    }
////    @GetMapping("/test2")
////    public String test() {
////        return "test";
////    }


    @GetMapping("/")
    public String home(HttpServletRequest request, Model model)  {

//        log.info("hello");
        return "menu/home";
    }

    @GetMapping("/about_cafe")
    public String about_cafe() {
        return "menu/about_cafe";
    }


    @GetMapping("/map")
    public String map() {
        return "menu/map";
    }

    @GetMapping("/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
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




}
