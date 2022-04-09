package stander.stander.controller;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Entity.Sit;
import stander.stander.model.Form.LoginForm;
import stander.stander.model.Entity.Member;
import stander.stander.model.Form.MemberForm;
import stander.stander.qr.QRUtil;
import stander.stander.service.MemberService;
import stander.stander.service.SitService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
public class MainController {

    private MemberService memberService;
    private SitService sitService;

    @Value("${file.dir}")
    private String fileDir;

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
    public String reserve(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session != null) {
            Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            if(member != null) {
                model.addAttribute("member", member);
            }
        }
        List<Sit> sits = sitService.findAll();
        model.addAttribute("sits", sits);
        return "reserve/reserve";
    }

    @GetMapping("/reserve/price")
    public String price(@RequestParam(name = "num", required=false) String num, Model model, HttpServletRequest request) {

        if(request.getSession(false) == null) {
            model.addAttribute("msg", "로그인이 필요합니다");
            return "login/login";
        }

        Member member = memberService.findById(7L);
        Long id = Long.parseLong(num);
//        if(sitService.check_member(member)) {
//            model.addAttribute("msg", "중복 예약 입니다.");
//            return "reserve/reserve";
//        }
//
//        if(sitService.check_sit(id)) {
//            model.addAttribute("msg", "좌석이 이미 예약 되어 있습니다.");
//            return "reserve/reserve";
//        }

        sitService.use(id, member);
        model.addAttribute("num", num);
        model.addAttribute("member", member);
        return "reserve/price";
    }

    @GetMapping("/reserve/clear")
    public String clear(Model model) {
        sitService.clearAll();
//        List<Sit> sits = sitService.findAll();
//        model.addAttribute("sits", sits);
        return "redirect:/reserve";
    }
    @GetMapping("/reserve/clear/{id}")
    public String clearOne(@PathVariable("id") Long id) {
        Member member = memberService.findById(id);
        sitService.clearOne(member);
        return "redirect:/reserve";
    }

    @GetMapping("/reserve/set_sit")
    public void reserve() {
        for(int i = 0; i < 30; i++) {
            Sit sit = new Sit();
            sitService.set(sit);
        }
    } //db에 좌석 생성

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

    @GetMapping("/login")
    public String post_login() {
        return "login/login";
    }

    @PostMapping("/login")
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


    @GetMapping("/login/join")
    public String post_join() {
        return "login/join";
    }

    @PostMapping("/login/join")
    public String create_join(@ModelAttribute MemberForm memberForm, Model model) {
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setUsername(memberForm.getUsername());
        member.setPassword(memberForm.getPassword());
        member.setPhonenum(memberForm.getPhonenum());
        member.setPersonnum_front(memberForm.getPersonnum_front());
        member.setPersonnum_back(memberForm.getPersonnum_back());
        memberService.join(member);
        model.addAttribute("member", memberForm);
//        return "login/join";
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

    @GetMapping("/codetest")
    public String makeQr(HttpServletRequest request) throws WriterException, IOException {

        int width = 150;
        int height = 150;

        HttpSession session = request.getSession(false);

        if(session == null) {
            return "menu/home";
        }

        Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);

        if(member == null) {
            return "menu/home";
        }
        String url = "http://localhost:8080/open/" + member.getId() ;

        String file_path = fileDir + member.getId()+"/";
        String file_name = "QR.png";
        QRUtil.makeQR(url , width , height , file_path , file_name);
        return "qr/test";
    }

    @ResponseBody
    @GetMapping("/img/{id}/QR.png")
    public Resource downloadImage(@PathVariable String id) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + id+"/QR.png");
    }

}
