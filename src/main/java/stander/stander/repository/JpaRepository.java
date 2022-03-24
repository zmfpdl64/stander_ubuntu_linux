package stander.stander.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Member;
import stander.stander.model.Test;

import javax.persistence.EntityManager;

@Transactional
public class JpaRepository {

    private final EntityManager em;

    public JpaRepository(EntityManager em) {
        this.em = em;
    }

    public Test save(Test member) {
        em.persist(member);
        return member;
    }
}
