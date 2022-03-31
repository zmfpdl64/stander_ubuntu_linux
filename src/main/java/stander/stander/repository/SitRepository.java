package stander.stander.repository;

import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;

import java.util.List;
import java.util.Optional;

public interface SitRepository {

    Sit set(Sit sit);
    Sit use(Long id, Member member);
    Sit exit(Long id);
    Optional<Sit> check_sit();

    List<Sit> findAll();

}
