package stander.stander.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@SpringBootTest
public class JpaSitRepositoryTest {

    @Autowired
    private EntityManager em;


    Logger log = LoggerFactory.getLogger(JpaRepositoryTest.class);

    @BeforeEach
    public void before() {
        Member member = new Member();
        Member member1 = new Member();

        Seat seat = new Seat();
        Seat seat1 = new Seat();

        member.setUsername("woojin");
        member.setPassword("1234");
        member.setAge(25L);
        member.setGender("male");

        member1.setUsername("jihyun");
        member1.setPassword("12345");
        member1.setAge(24L);
        member1.setGender("male");

        em.persist(member);
        em.persist(member1);

        seat.setSeat_num("1");
        seat.setMember(member);

        seat1.setSeat_num("2");
        seat1.setMember(member1);

        em.persist(seat);
        em.persist(seat1);

    }

//    @Test
//    public void save() {
//    }

    @Test
    public void findByMember() {
        Member findMember = em.find(Member.class, 1L);
        List<Seat> seat = em.createQuery("select m from Seat m where m.member = :member", Seat.class)
                .setParameter("member", findMember)
                .getResultList();
        Assertions.assertThat(seat.size()).isEqualTo(1);
        Assertions.assertThat(seat.get(0).getMember().getUsername()).isEqualTo("woojin");

    }

    @Test
    public void merge() {
        Member findMember = em.find(Member.class, 2L);
        Seat findSeat = em.find(Seat.class, 2L);
        findSeat.setMember(null);
        Seat mergeSeat = em.merge(findSeat);
        List<Seat> seat = em.createQuery("select m from Seat m where m.member is null", Seat.class)
                .getResultList();

        log.info("Seat.Member = {}", mergeSeat.getMember());
        for(Seat seat1 : seat) {
            log.info("seat.Member = {}", seat1.getMember());
        }
//        if(seat.get(0).getMember() == null) {
//            log.info("seat.get(0).getMember() = {}", seat.get(0).getMember());
//        }
    }

    @Test
    public void findUseMember() {
        Member findMember = em.find(Member.class, 2L);
        Seat findSeat = em.find(Seat.class, 2L);
        findSeat.setMember(null);
        Seat mergeSeat = em.merge(findSeat);

        List<Seat> seat = em.createQuery("select m from Seat m where m.member is not null", Seat.class)
                .getResultList();

        for(Seat seat1 : seat) {
            log.info("seat.Member = {}", seat1.getMember());
        }


    }

    @Test
    public void findById() {
        Seat seat = em.find(Seat.class, 1L);
        Assertions.assertThat(seat.getId()).isEqualTo(1L);
    }

    @Test
    public void clearById() {
        
    }



    @Test
    public void deleteMember() {
    }

    @Test
    public void findAll() {
        List<Seat> result = em.createQuery("select m from Seat m", Seat.class).getResultList();

        for(Seat seat : result) {
            log.info("seat.Member = {}", seat.getMember());
        }
    }
}