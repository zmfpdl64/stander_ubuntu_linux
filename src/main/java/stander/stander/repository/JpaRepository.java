package stander.stander.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpaRepository implements stander.stander.repository.Repository {

    private final EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {


        if(member.getId() == null)
            em.persist(member);
        else
            em.merge(member);
        return member;
    }

    public Member merge(Member member) {
        em.merge(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        Member member = em.find(Member.class, id);
        return member;
    }

    @Override
    public Member findByUsername(String username) {
        List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username).getResultList();
        return result.size() == 0 ? null : result.get(0);
    }

    @Override
    public Optional<Member> findByPasswd(String password) {
        List<Member> result = em.createQuery("select m from Member m where m.password = :password", Member.class)
                .setParameter("password", password).getResultList();
        return result.stream().findAny();
    }
    public Member findByEmail(String email) {
        Member result = em.createQuery("select m from Member m where m.email : email", Member.class)
                .setParameter("email", email)
                .getSingleResult();
        return result == null ? null : result;

    }

    public List<Member> findAll(String username) {
        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

        return result;
    }
}
