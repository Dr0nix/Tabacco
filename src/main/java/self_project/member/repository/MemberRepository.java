package self_project.member.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import self_project.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
