package self_project.member.service;

import self_project.exception.BusinessLogicException;
import self_project.exception.ExceptionCode;
import self_project.member.entity.Member;
import self_project.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 유저 생성
    public Member createMember(Member member) {
        member.setCreatedAt(LocalDateTime.now());
        member.setModifiedAt(LocalDateTime.now());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        return memberRepository.save(member);
    }

    // 단일 유저 찾기
    public Member findMember(long memberId) {return findVerifiedMember(memberId);}

    // 유저 전체 찾기
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    // 유저 수정
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getMemberState())
                .ifPresent(memberState -> findMember.setMemberState(memberState));

        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(member);
    }

    // 유저 삭제
    public Member deleteMember(long memberId) {
        Member findMember = findVerifiedMember(memberId);

        findMember.setMemberState(Member.MemberState.DELETED);
        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findMember);
    }

    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember =
                memberRepository.findById(memberId);
        Member findMember =
                optionalMember.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        if (findMember.getMemberState() != Member.MemberState.ACTIVE) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }
        return findMember;
    }
}
