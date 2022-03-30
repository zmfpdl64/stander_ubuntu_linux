package stander.stander.model.Entity;

import javax.persistence.*;

@Entity
public class Sit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;

    private Long sit_id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSit_id() {
        return sit_id;
    }

    public void setSit_id(Long sit_id) {
        this.sit_id = sit_id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
