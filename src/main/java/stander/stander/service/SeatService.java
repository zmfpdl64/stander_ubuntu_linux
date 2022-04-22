package stander.stander.service;

import org.springframework.transaction.annotation.Transactional;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.repository.JpaSitRepository;

import java.util.List;

@Transactional
public class SeatService {

    private JpaSitRepository sitRepository;

    public SeatService(JpaSitRepository sitRepository) {
        this.sitRepository = sitRepository;
    }

    public void save(Seat seat) {
        sitRepository.save(seat);
    }

    public void use(Long id, Member member){
        sitRepository.merge(id, member);
    }

    public void clearOne(Member member) {
        Seat seat = sitRepository.findByMember(member);
        seat.setMember(null);
    }
    public void clearAll() {
        List<Seat> result = sitRepository.findAll();
        for(Seat seat : result) {
            seat.setMember(null);
        }
    }

    public Seat findMember(Member member) {
        Seat seat = sitRepository.findByMember(member);
        return seat;
    }

    public Seat findById(Long id) {
        Seat seat = sitRepository.findById(id);
        return seat;
    }

    public List<Seat> findAll() {
        return sitRepository.findAll();

    }

    public Boolean check_member(Member member) {
        List<Seat> result = sitRepository.findAll();
        for( Seat seat : result) {
            if(seat.getMember() == member) {     //중복 예약했을 때
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
