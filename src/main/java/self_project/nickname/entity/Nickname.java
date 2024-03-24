package self_project.nickname.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import self_project.tobacco.entity.Tobacco;

@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Nickname {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String nickname;

    @Column
    @ManyToOne
    @JoinColumn(name = "tobaccoId")
    private Tobacco tobacco;
}
