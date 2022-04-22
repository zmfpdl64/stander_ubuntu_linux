package stander.stander.repository;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import javax.persistence.EntityManager;
import java.util.List;


@Transactional
@SpringBootTest
public class JpaRepositoryTest {

    @Autowired
    private EntityManager em;

    Logger log = LoggerFactory.getLogger(JpaRepositoryTest.class);

    @BeforeEach
    public void before() {
        Member member = new Member();
        Member member1 = new Member();

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
    }

//    @Test
//    public void save() {
//
//    }

    @Test
    public void findById() {
        Member findMember = em.find(Member.class, 1L);
        Assertions.assertThat(findMember.getId()).isEqualTo(1L);
        log.info("1L :  {}", findMember.getId());
    }

    @Test
    public void findByUsername() {
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "woojin")
                .getSingleResult();
        Assertions.assertThat(findMember.getUsername()).isEqualTo("woojin");

    }

    @Test
    public void findByPasswd() {
        Member findMember = em.createQuery("select m from Member m where m.password = :password", Member.class)
                .setParameter("password", "1234")
                .getSingleResult();
        Assertions.assertThat(findMember.getUsername()).isEqualTo("1234");
    }

    @Test
    public void findAll() {

        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }


}