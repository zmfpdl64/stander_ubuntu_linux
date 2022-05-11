package stander.stander.controller;

import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stander.stander.model.Entity.Member;
import stander.stander.qr.QRUtil;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;
import stander.stander.web.SessionConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/qr")
public class QRController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private SeatService sitService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/create")
    public String makeQr(HttpServletRequest request)
            throws WriterException, IOException {



        int width = 150;
        int height = 150;

        HttpSession session = request.getSession(false);

        if (session == null) {
            return "menu/home";
        }

        Member member = (Member) session
                .getAttribute(SessionConstants.LOGIN_MEMBER);

        if (member == null) {
            return "menu/home";
        }
        String url = "http://localhost:8080/open/" + member.getId();

        String file_path = fileDir + member.getId() + "/";
        String file_name = "QRCODE.jpg";
        String full_path = file_path + file_name;
        QRUtil.makeQR(url, file_path, file_name);


        return "qr/test";
    }

//    @ResponseBody
//    @GetMapping("/image/{id}")
//    public Resource downloadImage(@PathVariable(name = "id") String id) throws
//            MalformedURLException {
//        String full_path = "file:///" + fileDir + id + "/QRCODE.jpg";
//        log.info("file_path={}", full_path);
////
//    }     return new UrlResource("file:C:/images/volley.png");
//////        return new UrlResource("file:///C:images/QRCODE.jpg");
////        return new UrlResource(full_path);
}


