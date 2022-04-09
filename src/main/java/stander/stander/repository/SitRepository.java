package stander.stander.repository;

import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;

import java.util.List;

public interface SitRepository {

    Sit set(Sit sit);
    Sit findByMember(Member member);
    Sit findById(Long id);
    Sit merge(Long id, Member member);
    Sit deleteMember(Member member);
    List<Sit> findAll();

}
