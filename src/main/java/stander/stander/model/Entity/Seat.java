package stander.stander.model.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String seat_num;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    private Boolean present_use;

    private Date check_in;
    private Date check_out;

}
