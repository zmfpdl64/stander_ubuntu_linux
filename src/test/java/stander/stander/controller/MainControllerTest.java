package stander.stander.controller;

import groovy.util.logging.Slf4j;
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
import stander.stander.model.Entity.Sit;
import stander.stander.service.MemberService;
import stander.stander.service.SitService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MainControllerTest {

    Logger log = LoggerFactory.getLogger(MainControllerTest.class);

    @Autowired
    MemberService memberService;
    @Autowired
    SitService sitService;

    @Test
    @Rollback(false)
    @Transactional
    public void post_join() {
        Member member = new Member();
        member.setName("woojin");
        member.setUsername("이우진");
        member.setPassword("1234");
        member.setPhonenum("01021106737");
        member.setPersonnum_front("123456");
        member.setPersonnum_back("0012345");
        memberService.join(member);

        Member findmember = memberService.findById(member.getId());
        Assertions.assertThat(member.getName()).isEqualTo(findmember.getName());
        Assertions.assertThat(member.getPassword()).isEqualTo(findmember.getPassword());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void reserve() {
        Member member = new Member();
        member.setName("woojin");
        member.setUsername("이우진");
        member.setPassword("1234");
        member.setPhonenum("01021106737");
        member.setPersonnum_front("123456");
        member.setPersonnum_back("0012345");
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
}