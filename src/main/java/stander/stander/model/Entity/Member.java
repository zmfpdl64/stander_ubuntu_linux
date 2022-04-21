package stander.stander.model.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter

public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private Long age;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private String qr;
    private int time;
    private Date check_in;
}
