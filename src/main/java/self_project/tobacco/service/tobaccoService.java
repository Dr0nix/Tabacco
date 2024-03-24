package self_project.tobacco.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self_project.exception.BusinessLogicException;
import self_project.exception.ExceptionCode;
import self_project.nickname.repository.NicknameRepository;
import self_project.tobacco.entity.Tobacco;
import self_project.tobacco.repository.TobaccoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class tobaccoService {
    private final TobaccoRepository tobaccoRepository;
    private final NicknameRepository nicknameRepository;

    public tobaccoService(TobaccoRepository tobaccoRepository, NicknameRepository nicknameRepository) {
        this.tobaccoRepository = tobaccoRepository;
        this.nicknameRepository = nicknameRepository;
    }


    // 담배 생성
    public Tobacco createTobacco(Tobacco tobacco) {
        tobacco.setCreatedAt(LocalDateTime.now());
        tobacco.setModifiedAt(LocalDateTime.now());

        return tobaccoRepository.save(tobacco);
    }

    public Tobacco findSingleTobacco(long tobaccoId) {
        Optional<Tobacco> optionalTobacco =
                tobaccoRepository.findById(tobaccoId);
        Tobacco findTobacco =
                optionalTobacco.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TOBACCO_NOT_FOUND));
        return findTobacco;
    }

    public List<Tobacco> findTobaccosByName(String name) {
        List<Tobacco> tobaccoList = tobaccoRepository.findByNameContaining(name);

        return tobaccoList;
    }

//    public List<Tobacco> findTobaccosByNickname(String nickName) {
//
//    }
}
