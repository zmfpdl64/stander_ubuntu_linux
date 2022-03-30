package stander.stander.repository;

import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByPasswd(String pswd) {
        List<Member> result = em.createQuery("select m from Member m where m.password = :passwd", Member.class)
                .setParameter("passwd", pswd).getResultList();
        return result.stream().findAny();
    }
}
