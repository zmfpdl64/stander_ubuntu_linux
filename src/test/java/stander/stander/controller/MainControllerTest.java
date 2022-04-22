package stander.stander.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;

import java.util.Comparator;
import java.util.List;

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
    SeatService sitService;

    @Test
    @Rollback(false)
    @Transactional
    public void post_join() {
        Member member = newMember();
        memberService.join(member);

        Member findmember = memberService.findById(member.getId());
        Assertions.assertThat(member.getUsername()).isEqualTo(findmember.getUsername());
        Assertions.assertThat(member.getPassword()).isEqualTo(findmember.getPassword());
    }

    private Member newMember() {
        Member member = new Member();
        member.setUsername("이우진");
        member.setPassword("1234");
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
        Seat sit1 = new Seat();

        sitService.save(sit1);

        sitService.use(1L, member);
        Seat sit = sitService.findMember(member);
        Assertions.assertThat(sit.getId()).isEqualTo(1L);
        log.info("sit.member_id = {}", sit.getMember());

        sitService.clearOne(member);
        Seat cleansit = sitService.findById(1L);
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
            List<Seat> sits = sitService.findAll();
            for (Seat sit : sits) {
                log.info("sit.getId() = {}", sit.getId());
            }
            sits.sort(new Comparator<Seat>() {
                @Override
                public int compare(Seat o1, Seat o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });
            for (Seat sit : sits) {
                log.info("sort sit.getId() = {}", sit.getId());
            }
        }
    }
}