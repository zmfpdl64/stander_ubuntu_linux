package stander.stander.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import stander.stander.repository.JpaRepository;
import stander.stander.repository.JpaSitRepository;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import java.io.InputStream;
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
    public SeatService sitService() throws SQLException {
        return new SeatService(sitrepository());
    }

//    @Bean
//    public JavaMailSender javaMailSender() {
//        return new JavaMailSender() {
//            @Override
//            public MimeMessage createMimeMessage() {
//                return null;
//            }
//
//            @Override
//            public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
//                return null;
//            }
//
//            @Override
//            public void send(MimeMessage mimeMessage) throws MailException {
//
//            }
//
//            @Override
//            public void send(MimeMessage... mimeMessages) throws MailException {
//
//            }
//
//            @Override
//            public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
//
//            }
//
//            @Override
//            public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
//
//            }
//
//            @Override
//            public void send(SimpleMailMessage simpleMessage) throws MailException {
//
//            }
//
//            @Override
//            public void send(SimpleMailMessage... simpleMessages) throws MailException {
//
//            }
//        };
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/images/");
    }
}
