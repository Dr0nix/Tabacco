package self_project.nickname.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self_project.nickname.entity.Nickname;

import java.util.List;

@Repository
public interface NicknameRepository extends JpaRepository<Nickname, Long> {
    List<Nickname> findByNicknameContaining(String nickname);
}
