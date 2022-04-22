package stander.stander.model.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class MemberForm {

        @NotBlank(message="아이디를 입력해주세요")
        private String username;
        @NotBlank(message="비밀번호를 입력해주세요")
        private String password;
        @NotBlank
        private String gender;
        @NotBlank
        private Long age;
        @Email(message="이메일 형식이 잘못되었습니다.")
        @NotNull
        private String email;

}
