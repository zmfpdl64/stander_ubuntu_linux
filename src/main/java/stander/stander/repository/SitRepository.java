package stander.stander.repository;

import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;

import java.util.List;

public interface SitRepository {

    Seat set(Seat sit);
    Seat findByMember(Member member);
    Seat findById(Long id);
    Seat merge(Long id, Member member);
    Seat deleteMember(Member member);
    List<Seat> findAll();

}
