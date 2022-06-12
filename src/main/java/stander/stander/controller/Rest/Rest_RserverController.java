package stander.stander.controller.Rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class Rest_RserverController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SeatService seatService;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${file.dir}")
    private String fileDir;

    @Value("${ip.address}")
    private String ip;

    @PostMapping("/rest_reserve")
    public Map<String, String> rest_reserve() {
        try{
            List<Seat> result = seatService.findUseSeat();
            Map<String, String> map = new HashMap<>();
            if (result == null) {
                return null;
            }

            for (Seat seat : result) {
                int time = seat.getMember().getTime();
                int day = time / (60 * 60 * 24);  // day *
                int hour = time % (60 * 60 * 24) / (60 * 60);
                int minute = time % (60 * 60) / 60;
                int second = time % 60;

                String[] user_name = seat.getMember().getName().split("");
                if (user_name.length >= 3) {
                    int length = user_name.length;
                    for (int i = 1; i < length - 1; i++) {
                        user_name[i] = "*";
                    }
                } else {
                    user_name[0] = "*";
                }
                String left_time = String.join("", user_name) + "\n" + day + "일 " + hour + "시간 " + minute + "분" + second + "초";
                System.out.println(left_time);
                map.put("seat" + seat.getSeat_num(), left_time);
                return map;
        }

        } catch(Exception e) {
            return null;
        }
        return null;
    }

    @PostMapping("/rest_reserve/complete")
    public Map<String, Object> reserve_complete(@RequestParam Map<String, String> map) {
        try{
            Map<String, Object> map1 = new HashMap<>();
            long id = Long.parseLong(map.get("id"));
            Member member = memberService.findById(id);

            if (seatService.check_sit(id)) {
                map1.put("status", "1");
                map1.put("seat", null);
                return map1;
            }
            else if (seatService.check_member(member)) {
                map1.put("status", "2");
                map1.put("seat", null);
                return map1;
            }
            else if(member.getTime() == 0) {
                map1.put("status", "3");
                map1.put("seat", null);
                return map1;
            }
            String url = "http://"+ ip +":8080/open/" + member.getId();
            member.setQr(url);
            Seat seat = new Seat();
            seat.setMember(member);
            seat.setSeat_num(map.get("seat_num"));
            seat.setPresent_use(true);
            seat.setCheck_in(new Date());
            member.setSeat(seat);
            seatService.save(seat);
            map1.put("member", member);

            return map1;
        }
        catch (Exception e) {
//            System.out.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
    @PostMapping("/rest_pay")
    public String rest_pay(@RequestParam Map<String,String> map) {
        try {
            Long id = Long.parseLong(map.get("id"));
            int time = Integer.parseInt(map.get("time"));
            Member member = memberService.findById(id);
            member.setTime(member.getTime() + time);
            memberService.modify(member);
            return "ok";
        }
        catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/rest_reserve/clear")
    public String clear(@RequestParam Map<String, String> map) {
        try{
            Long id = Long.parseLong(map.get("id"));
            Member member = memberService.findById(id);
            member.setQr(null);

            seatService.clearOne(member);

            return "ok";
        }
        catch(Exception e) {
            return null;
        }
    }
    @PostMapping("/rest_reserve/clearall")
    public String clear() {
        try {
            seatService.clearAll();
            return "ok";
        } catch (Exception e) {
            return null;
        }
    }
}
