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
    public Seat save(Seat seat) {
//        Seat findSeat = findById(seat.getId()); //아직 db에 저장하지 않아 id값이 생성이 안됐다.
//        if(findSeat == null) {
//            em.persist(seat);
//        }
//        else{
//            seat.setMember(null);
//            em.merge(seat);
//        }
        em.persist(seat);
        return seat;
    }

    @Override
    public Seat findByMember(Member member) {
        List<Seat> result = em.createQuery("select m from Seat m where m.member = :member", Seat.class)
                .setParameter("member", member)
                .getResultList();
        if (result.size() != 0) {
            Seat seat = result.get(0);
            return seat;
        }
        return null;
    }

    public List<Seat> findUseMember() {     //좌석이 이용되고 있으면 반환된다.
        List<Seat> result = em.createQuery("select m from Seat m where m.present_use = :present_use", Seat.class)
                .setParameter("present_use", true)
                .getResultList();
        log.info("result = {}", result);
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public Seat findById(Long id) {
        Seat seat = em.find(Seat.class, id);
        return seat;
    }

    public Seat clearById(Long id) {
        Seat seat = em.find(Seat.class, id);
        seat.setMember(null);
        em.merge(seat);
        return seat;
    }


    @Override
    public Seat merge(Long id, Member member) {
        Seat seat = em.find(Seat.class, id);
        seat.setMember(member);
        log.info("Member Id={}", member.getId());
        log.info("Sit Id={}", seat.getId());
        Seat mergeSeat = em.merge(seat);

        return seat;
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
        List<Seat> result = em.createQuery("select m from Seat m", Seat.class).getResultList();
        if (result.isEmpty()) {
            return null;
        }
        return result;
    }
}



