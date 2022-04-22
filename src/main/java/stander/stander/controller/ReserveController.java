package stander.stander.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SeatService sitService;


    @GetMapping
    public String reserve(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if(session != null) {
            Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
            if(member != null) {
                model.addAttribute("member", member);
            }
            else{
                model.addAttribute("msg", "회원정보가 없습니다");
                return "menu/home";
            }
        }
        else {
            model.addAttribute("msg", "세션이 없습니다");
            return "menu/home";
        }
        List<Seat> sits = sitService.findAll();
        sortSit(sits);
        model.addAttribute("sits", sits);
        return "reserve/reserve";
    }

    @GetMapping("/price")
    public String price(@RequestParam(name = "num", required=false) String num, Model model, HttpServletRequest request) {

        Long id = Long.parseLong(num);


        HttpSession session = request.getSession(false);
        if(session == null) {
            model.addAttribute("msg", "로그인이 필요합니다");
            return "login/login";
        }

        Member member = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
        if(member == null) {
            model.addAttribute("msg", "회원이 존재하지 않습니다");
            return "login/join";
        }
        List<Seat> sits = sitService.findAll();

        if( sitService.check_member(member, id)) {
//            for(Sit sit : sits) {
//                log.info("sit.getId() = {}", sit.getId());
//            }
            sortSit(sits);
            for(Seat sit : sits) {
                log.info("sort sit.getId() = {}", sit.getId());
            }
            model.addAttribute("sits", sits);

            model.addAttribute("msg", "중복으로 예약이 되어있습니다.");

            model.addAttribute("member", member);
            return "reserve/reserve";
        }

        sitService.use(id, member);

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

    private void sortSit(List<Seat> sits) {
        sits.sort(new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
    }

    @GetMapping("/clear")
    public String clear(Model model) {
        sitService.clearAll();
//        List<Sit> sits = sitService.findAll();
//        model.addAttribute("sits", sits);
        return "redirect:/reserve";
    }
    @GetMapping("/clear/{id}")
    public String clearOne(@PathVariable("id") String id) {

        Member member = memberService.findById(Long.valueOf(id));
        sitService.clearOne(member);
        return "redirect:/reserve";
    }

    @GetMapping("/set_sit")
    public void reserve() {
        for(int i = 0; i < 30; i++) {
            Seat sit = new Seat();
            sitService.save(sit);
        }
    } //db에 좌석 생성
}
