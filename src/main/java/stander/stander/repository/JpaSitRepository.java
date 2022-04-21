package stander.stander.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
public class JpaSitRepository implements SitRepository {

    private EntityManager em;

    public JpaSitRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Seat set(Seat sit) {
        em.persist(sit);
        return sit;
    }

    @Override
    public Seat findByMember(Member member) {
        TypedQuery<Seat> sitQuery = em.createQuery("select m from Sit m where m.member = :member", Seat.class)
                .setParameter("member", member);
        List<Seat> result = sitQuery.getResultList();
        if (result.size() != 0) {
            Seat sit = result.get(0);
            return sit;
        }
        return null;
    }

    @Override
    public Seat findById(Long id) {
        Seat sit = em.find(Seat.class, id);
        return sit;
    }

    public Seat clearById(Long id) {
        Seat sit = em.find(Seat.class, id);
        sit.setMember(null);
        em.merge(sit);
        return sit;
    }

    @Override
    public Seat merge(Long id, Member member) {
        Seat sit = em.find(Seat.class, id);
        sit.setMember(member);
        log.info("Member Id={}", member.getId());
        log.info("Sit Id={}", sit.getId());
        Seat mergeSeat = em.merge(sit);

        return sit;
    }

    @Override
    public Seat deleteMember(Member member) {
        Seat sit = findByMember(member);

        log.info("Member_ID = {}", sit.getMember().getId());
        log.info("Sit_ID = {}", sit.getId());
        sit.setMember(null);
        em.merge(sit);
        return sit;
    }

    @Override
    public List<Seat> findAll() {
        List<Seat> result = em.createQuery("select m from Sit m", Seat.class).getResultList();
        return result;
    }
}



