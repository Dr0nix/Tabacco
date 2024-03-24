package self_project.tobacco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self_project.tobacco.entity.Tobacco;

import java.util.List;
import java.util.Optional;

@Repository
public interface TobaccoRepository extends JpaRepository<Tobacco, Long> {
    Optional<Tobacco> findByName(String name);

    List<Tobacco> findByNameContaining(String name);
}
