package stander.stander.model.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Sit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Column(name="sit_id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
