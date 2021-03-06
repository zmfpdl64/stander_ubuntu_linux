package stander.stander.controller.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import stander.stander.model.Entity.Member;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;

@RestController
public class Rest_IotController {

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

    @GetMapping("/open/{id}")   //IOT에서 QR코드가 존재하면 OPEN이 되게 하는 코드이다.
    public String open(@PathVariable("id") Long id) {
        try {
            Member member = memberService.findById(id);
            if(member.getQr() == null) return null;
            System.out.println(member.getQr());
            return "ok";
        }
        catch (Exception e) {
            return null;
        }

    }
}
