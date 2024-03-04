package self_project.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import self_project.member.entity.Member;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Post {
        @Email
        @NotBlank
        private String email;

        @NotBlank(message = "닉네임은 공백이 허용되지 않습니다.")
        private String name;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z\\\\d`~!@#$%^&*()-_=+]{8,15}$",
                message = "영어와 숫자, 특수문자를 사용해야 하며 8~15자리로 설정해야합니다.")
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Patch {
        private long memberId;

        @NotBlank(message = "닉네입은 공백이 허용되지 않습니다.")
        private String name;

        private Member.MemberState memberState;
    }

    @AllArgsConstructor
    @Getter
    public static class response {
        private long memberId;
        private String email;
        private String name;
        private Member.MemberState memberState;
    }
}
