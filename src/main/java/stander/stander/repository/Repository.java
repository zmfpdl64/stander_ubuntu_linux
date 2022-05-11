package stander.stander.repository;

import stander.stander.model.Entity.Member;

import java.util.Optional;

public interface Repository {

    Member save(Member member);
    Member findById(Long id);
    Member findByUsername(String username);
    Optional<Member> findByPasswd(String password);

}
