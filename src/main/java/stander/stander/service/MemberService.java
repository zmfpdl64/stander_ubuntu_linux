package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Member;
import stander.stander.repository.Repository;

@Transactional
public class MemberService {

    private Repository repository;


    public MemberService(Repository repository) {
        this.repository = repository;
    }

    public void join(Member member) {
//        validName(member);

        repository.save(member);
    }

    private void validName(Member member) {
        if(repository.findByName(member.getName()) == null)
        {
            throw new IllegalStateException("이름이 중복됩니다.");
        }
    }
}
