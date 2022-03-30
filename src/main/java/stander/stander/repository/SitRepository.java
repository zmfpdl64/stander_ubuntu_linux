package stander.stander.repository;

import stander.stander.model.Entity.Sit;

import java.util.List;
import java.util.Optional;

public interface SitRepository {

    Sit use(Sit sit);
    Sit exit(Long id);
    Optional<Sit> check_sit();

    List<Sit> findAll();

}
