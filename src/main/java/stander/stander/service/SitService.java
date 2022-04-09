package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Sit;
import stander.stander.repository.JpaSitRepository;

import java.util.List;

@Transactional
public class SitService {

    private JpaSitRepository sitRepository;

    public SitService(JpaSitRepository sitRepository) {
        this.sitRepository = sitRepository;
    }

    public void set(Sit sit) {
        sitRepository.set(sit);
    }

    public void use(Long id, Member member){
        sitRepository.merge(id, member);
    }

    public void clearOne(Member member) {
        Sit sit = sitRepository.findByMember(member);
        sit.setMember(null);
    }
    public void clearAll() {
        List<Sit> result = sitRepository.findAll();
        for(Sit sit : result) {
            sit.setMember(null);
        }
    }

    public List<Sit> findAll() {
        return sitRepository.findAll();
    }

    public Boolean check_member(Member member) {
        return null;
    }
    public Boolean check_sit(Long id) {
        return null;
    }

    public void use() {
    }
}
