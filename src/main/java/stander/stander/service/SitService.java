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

    public void use(Member member, Long id) {
        sitRepository.use(id, member);
    }

    public void exit(Sit sit) {
        sitRepository.exit(sit.getSit_id());
    }

    public void clear() {
        sitRepository.clear();
    }

    public List<Sit> findAll() {
        return sitRepository.findAll();
    }

    public Boolean check_member(Member member) {
        return sitRepository.check_member(member); //존재하면 true 없으면 false
    }
    public Boolean check_sit(Long id) {
        return sitRepository.check_sit(id);
    }
}
