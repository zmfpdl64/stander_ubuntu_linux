package stander.stander.model.Form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class MemberForm {

        private Long id;
        private String username;
        private String password;
        private String gender;
        private Long age;
        private String seat;
        private String qr;
        private int time;
        private Date check_in;
}
