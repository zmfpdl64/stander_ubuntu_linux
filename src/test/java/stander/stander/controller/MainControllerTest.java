package stander.stander.controller;

import groovy.util.logging.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;
import stander.stander.service.MemberService;
import stander.stander.service.SitService;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = MainController.class)
class MainControllerTest {

//    @Autowired
//    private MockMvc mvc;

    Logger log = LoggerFactory.getLogger(MainControllerTest.class);

    @Autowired
    MemberService memberService;
    @Autowired
    SitService sitService;

    @Test
    @Rollback(false)
    @Transactional
    public void post_join() {
        Member member = newMember();
        memberService.join(member);

        Member findmember = memberService.findById(member.getId());
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
        Assertions.assertThat(member.getPassword()).isEqualTo(findmember.getPassword());
    }

    private Member newMember() {
        Member member = new Member();
        member.setName("woojin");
        member.setUsername("이우진");
        member.setPassword("1234");
        member.setPhonenum("01021106737");
        member.setPersonnum_front("123456");
        member.setPersonnum_back("0012345");
        return member;
    }

    @Test
    @Rollback(false)
    @Transactional
    public void reserve() {
        Member member = newMember();
        memberService.join(member);

//        for(int i = 0; i < 15; i++) {
//            Sit sit = new Sit();
//            sitService.set(sit);
//        }
        Sit sit1 = new Sit();

        sitService.set(sit1);

        sitService.use(1L, member);
        Sit sit = sitService.findMember(member);
        Assertions.assertThat(sit.getId()).isEqualTo(1L);
        log.info("sit.member_id = {}", sit.getMember());

        sitService.clearOne(member);
        Sit cleansit = sitService.findById(1L);
        Assertions.assertThat(cleansit.getMember()).isEqualTo(null);
        log.info("sit.member_id = {}", cleansit.getMember());

//        Assertions.assertThat(1).isEqualTo(2);

    }
    @Test
    @Rollback(false)
    @Transactional
    public void sort_sit() throws Exception {

        Member member = newMember();
        memberService.join(member);
        Long id = 10L;
        sitService.use(id, member);

        if (sitService.check_member(member, id)) {
            List<Sit> sits = sitService.findAll();
            for (Sit sit : sits) {
                log.info("sit.getId() = {}", sit.getId());
            }
            sits.sort(new Comparator<Sit>() {
                @Override
                public int compare(Sit o1, Sit o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });
            for (Sit sit : sits) {
                log.info("sort sit.getId() = {}", sit.getId());
            }
        }
    }
}