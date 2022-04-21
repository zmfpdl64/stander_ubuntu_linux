package stander.stander.model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String seat_num;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Date check_in;
    private Date check_out;

}
