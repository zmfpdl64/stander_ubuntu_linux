package stander.stander.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stander.stander.repository.JpaRepository;
import stander.stander.repository.JpaSitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Configuration
public class Config {

    private EntityManager em;

    public Config(EntityManager em) {
        this.em = em;
    }

    @Bean
    public JpaRepository repository() throws SQLException {
        return new JpaRepository(em);
    }

    @Bean
    public JpaSitRepository sitrepository() throws SQLException {
        return new JpaSitRepository(em);
    }

    @Bean
    public MemberService memberService() throws SQLException {
        return new MemberService(repository());
    }

    @Bean
    public SitService sitService() throws SQLException {
        return new SitService(sitrepository());
    }
}
