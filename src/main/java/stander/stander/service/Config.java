package stander.stander.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import stander.stander.repository.JpaRepository;
import stander.stander.repository.JpaSitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;

@Configuration
public class Config implements WebMvcConfigurer {

    @Value("${file.dir")
    private String fileDir;

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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/images/");
//                .addResourceLocations("file:D:\\대학교\\4학년\\STANDER\\STANDER_GIT\\stander\\src\\main\\resources\\static\\img/")

    }
}
