package stander.stander.repository;

import stander.stander.model.Member;

public interface Repository {

    Member save(Member member);
    Member findById(Long id);
    Member findByName(String name);
    Member findByPasswd(String pswd);

}
