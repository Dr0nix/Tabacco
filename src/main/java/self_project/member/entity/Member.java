package self_project.member.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import demo.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    private String password;

    @Column
    private MemberRole memberRole;

    @Column
    private MemberState memberState;

    public static enum MemberRole {
        OPERATOR("관리자"),
        USER("회원");

        @Getter
        private String role;

        MemberRole(String role) {
            this.role = role;
        }
    }

    public static enum MemberState {
        ACTIVE("활성"),
        DELETED("탈퇴");

        @Getter
        private String state;

        MemberState(String state) {
            this.state = state;
        }
    }

}
