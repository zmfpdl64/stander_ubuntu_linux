package stander.stander.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JpaSitRepository implements SitRepository {

    private EntityManager em;

    public JpaSitRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Sit set(Sit sit) {
        em.persist(sit);
        return sit;
    }

    @Override
    public Sit findByMember(Member member) {
        TypedQuery<Sit> sitQuery = em.createQuery("select m from Sit m where m.member = :member", Sit.class)
                .setParameter("member", member);
        List<Sit> result = sitQuery.getResultList();
        if (result.size() != 0) {
            Sit sit = result.get(0);
            return sit;
        }
        return null;
    }

    @Override
    public Sit findById(Long id) {
        Sit sit = em.find(Sit.class, id);
        return sit;
    }

    public Sit clearById(Long id) {
        Sit sit = em.find(Sit.class, id);
        sit.setMember(null);
        em.merge(sit);
        return sit;
    }

    @Override
    public Sit merge(Long id, Member member) {
        Sit sit = em.find(Sit.class, id);
        sit.setMember(member);
        log.info("Member Id={}", member.getId());
        log.info("Sit Id={}", sit.getId());
        Sit mergeSit = em.merge(sit);

        return sit;
    }

    @Override
    public Sit deleteMember(Member member) {
        Sit sit = findByMember(member);

        log.info("Member_ID = {}", sit.getMember().getId());
        log.info("Sit_ID = {}", sit.getId());
        sit.setMember(null);
        em.merge(sit);
        return sit;
    }

    @Override
    public List<Sit> findAll() {
        List<Sit> result = em.createQuery("select m from Sit m", Sit.class).getResultList();
        return result;
    }
}
