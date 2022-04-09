package stander.stander.model.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter

public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
//    @Column(name="member_id")
    private Long id;
    private String username;
    private String name;
    private String password;
    private String phonenum;
    private String personnum_front;
    private String personnum_back;
}
