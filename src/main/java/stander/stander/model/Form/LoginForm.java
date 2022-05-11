package stander.stander.model.Form;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginForm {
    @NotBlank(message = "아이디를 입력해야합니다.")
    private String username;
    @NotBlank(message = "비밀번호가 필요합니다.")
    private String password;


}
