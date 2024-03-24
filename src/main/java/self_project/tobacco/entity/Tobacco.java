package self_project.tobacco.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import self_project.audit.Auditable;
import self_project.nickname.entity.Nickname;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Tobacco extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tobaccoId")
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    // Nickname 클래스 1:n 매핑
    @OneToMany(mappedBy = "tobacco", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Nickname> nicknames = new ArrayList<>();

}
