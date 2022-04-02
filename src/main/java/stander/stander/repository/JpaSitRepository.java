package stander.stander.repository;

import org.springframework.stereotype.Repository;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
    public Sit use(Long id, Member member) {
        Sit sit = em.find(Sit.class, id);
        sit.setMember(member);
        em.merge(sit);

        return sit;
    }

    @Override
    public Sit exit(Long sit_id) {
        TypedQuery<Sit> sitQuery = em.createQuery("select m from Sit m where m.sit_id = :sit_id", Sit.class);
        List<Sit> result = sitQuery.getResultList();
        Sit sit = result.get(0);
        return sit;
    }

    @Override
    public List<Sit> clear() {
        List<Sit> sits = em.createQuery("select m from Sit m", Sit.class).getResultList();
        for(Sit sit: sits) {
            sit.setMember(null);
            em.merge(sit);
        }
        return sits;
    }

    @Override
    public Boolean check_member(Member member) { //특정사용자가 좌석에 있다면 true 없다면 false를 반환한다.
        List<Sit> sits = findAll();
        for(Sit sit : sits) {
            if(sit.getMember().equals(member)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean check_sit(Long id) { //특정 좌석의 사용자가 없다면 false 있다면 true 반환한다.
        List<Sit> sits = findAll();
        for(Sit sit : sits) {
            if(sit.getId().equals(id) && sit.getMember() != null ) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Sit> findAll() {
        List<Sit> result = em.createQuery("select m from Sit m", Sit.class).getResultList();
        return result;
    }
}
