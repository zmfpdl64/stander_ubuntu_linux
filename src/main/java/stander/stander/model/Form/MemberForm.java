package stander.stander.model.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class MemberForm {

        private String name;
        private String username;
        private String password;
        private String gender;
        private String age;
        @Email(message="이메일 형식이 잘못되었습니다.")
        private String email;

}
