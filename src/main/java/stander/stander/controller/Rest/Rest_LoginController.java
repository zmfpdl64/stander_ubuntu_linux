package stander.stander.controller.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import stander.stander.model.Entity.Member;
import stander.stander.model.Form.LoginForm;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;

import java.util.Map;

@RestController
public class Rest_LoginController {

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

    @ResponseBody
    @PostMapping("/rest-login") //안드로이드에서 rest api를 이용한 로그인 방식이다.
    public Member rest_login(@ModelAttribute LoginForm loginForm){
        try {
            //System.out.println(map);
            //System.out.println(map.get("username") + map.get("password"));
//            loginForm.setUsername(map.get("username"));
//            loginForm.setPassword(map.get("password"));
            loginForm.setUsername(loginForm.getUsername());
            loginForm.setUsername(loginForm.getUsername());
            Member member = memberService.login(loginForm);

            return member;
        }
        catch (Exception e ) {
            return null;
        }

    }
//    @ResponseBody
//    @PostMapping("/rest-login")
//    public Map<String, Object> rest_login(@RequestParam("username")String username, @RequestParam("password") String password) {
//        try {
//            LoginForm loginForm = new LoginForm();
//            loginForm.setUsername(username);
//            loginForm.setPassword(password);
//            Member member = memberService.login(loginForm);
//            List<Seat> result = seatService.findUseSeat();
//            Map<String, Object> map = new HashMap<>();
//            map.put("member", member);
//            map.put("seats", result);
//            return map;
//        }
//        catch (Exception e ) {
//            return null;
//        }
//
//    }
    @ResponseBody
    @PostMapping("/rest_join")  //안드로이드에서 name, username, password, email을 입력받아 회원가입하는 코드이다.
    public String rest_join(@RequestParam Map<String, String> map) {
        try {
            Member member;
            member = set_join(map.get("name)"), map.get("username"), map.get("password"), map.get("email"));
            memberService.join(member);
            return "ok";
        }catch(Exception e){
            return null;
        }
    }

    private Member set_join(String name, String username, String password, String email) {
        Member member = new Member();
        member.setName(name);
        member.setUsername(username);
        member.setPassword(password);
        member.setEmail(email);
        return member;
    }
}
