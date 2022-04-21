package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.repository.JpaSitRepository;

import java.util.List;

@Transactional
public class SitService {

    private JpaSitRepository sitRepository;

    public SitService(JpaSitRepository sitRepository) {
        this.sitRepository = sitRepository;
    }

    public void set(Seat sit) {
        sitRepository.set(sit);
    }

    public void use(Long id, Member member){
        sitRepository.merge(id, member);
    }

    public void clearOne(Member member) {
        Seat sit = sitRepository.findByMember(member);
        sit.setMember(null);
    }
    public void clearAll() {
        List<Seat> result = sitRepository.findAll();
        for(Seat sit : result) {
            sit.setMember(null);
        }
    }

    public Seat findMember(Member member) {
        Seat sit = sitRepository.findByMember(member);
        return sit;
    }

    public Seat findById(Long id) {
        Seat sit = sitRepository.findById(id);
        return sit;
    }

    public List<Seat> findAll() {
        return sitRepository.findAll();

    }

    public Boolean check_member(Member member, Long id) {
        List<Seat> result = sitRepository.findAll();
        int count = 0;
        for( Seat sit : result) {
            if(sit.getMember() == member) {     //중복 예약했을 때
                count++;
                if(count >= 2)
                    sitRepository.clearById(id);
                    return true;
            }
        }
        return false;
    }
    public Boolean check_sit(Long id) {
        return null;
    }

    public void use() {
    }
}
