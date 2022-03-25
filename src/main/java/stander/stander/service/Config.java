package stander.stander.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stander.stander.repository.JpaRepository;

import javax.persistence.EntityManager;
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
    public MemberService memberService() throws SQLException {
        return new MemberService(repository());
    }
}
