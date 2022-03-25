package stander.stander.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Member;
import stander.stander.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaRepository implements stander.stander.repository.Repository {

    private final EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    @Override
    public Member findByName(String name) {
        Member member = em.createQuery("m select from Member m where m.name = :name", Member.class).setParameter("name", name).getSingleResult();
        return member;
    }

    @Override
    public Member findByPasswd(String pswd) {
        Member member = em.createQuery("m select from Memer m where m.password = :password", Member.class).setParameter("password", pswd).getSingleResult();
        return member;
    }
}
