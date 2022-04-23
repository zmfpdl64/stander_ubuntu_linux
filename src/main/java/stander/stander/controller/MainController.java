package stander.stander.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

        List<Seat> same_Member = sitService.find_Usage_History(member);
        SimpleDateFormat simple = new SimpleDateFormat("yyyy년 MM월 dd일 a HH시 mm분");
        List<String> usage_histories = new ArrayList<>();
        for(Seat seat: same_Member) {
            if(seat.getCheck_in() != null) {
                usage_histories.add(simple.format(seat.getCheck_in()) + " 입실하셨습니다.");
            }
            if(seat.getCheck_out() != null) {
                usage_histories.add(simple.format(seat.getCheck_out()) + " 퇴실하셨습니다.");
            }
        }

        int time =member.getTime();
        int day = time / (60 * 60 * 24);  // day *
        int hour = time % (60 * 60 * 24) /(60 * 60)  ;
        int minute = time % (60 * 60) / 60;
        int second = time % 60;
        String user_name = member.getUsername();
        String left_time = day + "일 "+ hour + "시간 " + minute + "분" ;

        model.addAttribute("member", member);
        model.addAttribute("usage_histories", usage_histories);
        model.addAttribute("left_time", left_time);

        return "menu/mypage";
    }




}
