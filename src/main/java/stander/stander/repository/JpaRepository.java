package stander.stander.repository;

import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public Optional<Member> findByUsername(String username) {
        List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username).getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByPasswd(String password) {
        List<Member> result = em.createQuery("select m from Member m where m.password = :password", Member.class)
                .setParameter("password", password).getResultList();
        return result.stream().findAny();
    }

    public List<Member> findAll(String username) {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

        return result;
    }
}
