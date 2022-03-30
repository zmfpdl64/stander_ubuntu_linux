package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Form.LoginForm;
import stander.stander.model.Entity.Member;
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
        if(repository.findByName(member.getName()).isEmpty())
        {
            throw new IllegalStateException("이름이 중복됩니다.");
        }
    }

    public Member login(LoginForm loginForm) {
//        Member member = repository.findById(7L);
//        System.out.println(member.getName() + member.getPassword());
        Member member = repository.findByName(loginForm.getName()).orElse(null);
        if( member != null) {
            if(loginForm.getName().equals(member.getName()) && loginForm.getPassword().equals(member.getPassword())) {
                System.out.println("로그인 성공했습니다.");
                return member;
            }
        }
        return null;
//        throw new IllegalArgumentException("로그인 실패했습니다.");
    }

    public Member findById(Long id) {
        Member member = repository.findById(id);
        return member;
    }

}
