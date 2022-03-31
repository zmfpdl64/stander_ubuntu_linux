package stander.stander.repository;

import org.springframework.stereotype.Repository;
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
    public Sit use(Sit sit) {
        return null;
    }

    @Override
    public Sit exit(Long sit_id) {
        TypedQuery<Sit> sitQuery = em.createQuery("select m from Sit m where m.sit_id = :sit_id", Sit.class);
        List<Sit> result = sitQuery.getResultList();
        Sit sit = result.get(0);
        return sit;
    }

    @Override
    public Optional<Sit> check_sit() {
        return Optional.empty();
    }

    @Override
    public List<Sit> findAll() {
        List<Sit> result = em.createQuery("select m from Sit m", Sit.class).getResultList();
        return result;
    }
}
