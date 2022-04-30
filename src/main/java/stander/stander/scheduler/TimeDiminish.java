package stander.stander.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stander.stander.model.Entity.Member;
import stander.stander.model.Entity.Seat;
import stander.stander.service.MemberService;
import stander.stander.service.SeatService;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class TimeDiminish {

    @Autowired
    private SeatService seatService;
    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0/1 * * * * *")
    public void timeDiminish() {
        List<Seat> useSeat = seatService.findUseSeat();
        for(Seat seat: useSeat) {
            int use_time = ((int) new Date().getTime() - (int) seat.getCheck_in().getTime()) / 1000;
            log.info("use_time = {}", use_time);

            int time = seat.getMember().getTime() - use_time;
            log.info("time = {}", time);

            if(seat.getMember().getTime() == 0) {
                Member member = seat.getMember();
                member.setQr(null);
                seatService.clearOne(member);
                memberService.modify(member);

            }
            else{
                seat.getMember().setTime(seat.getMember().getTime() - 1);
                memberService.modify(seat.getMember());
            }

        }
    }

}
