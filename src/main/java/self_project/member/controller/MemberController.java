package self_project.member.controller;



import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import self_project.member.dto.MemberDto;
import self_project.member.entity.Member;
import self_project.member.mapper.MemberMapper;
import self_project.member.service.MemberService;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@Validated
public class MemberController {
//    private final static String MEMBER_DEFAULT_URL = "/tabaccoHelper/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

    // 멤버 생성
    @PostMapping("/post")
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memberPostToMember(requestBody);
        System.out.println("멤버 등록됨");

        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    // 멤버 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity patchMember(
            @PathVariable("memberId") @Positive long memberId,
            @Valid @RequestBody MemberDto.Patch requestBody) {
        requestBody.addMemberId(memberId);

        Member member = memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(response(member), HttpStatus.OK);
    }

    // 단일 멤버 조회
    @GetMapping("/{memberId}")
    public ResponseEntity getMember(@PathVariable("memberId") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(response(member), HttpStatus.OK);
    }

    // 전체 멤버 조회
    @GetMapping
    public ResponseEntity getMembers() {
        List<Member> members = memberService.findAllMembers();
        return new ResponseEntity<>(mapper.membersToMemberResponses(members), HttpStatus.OK);
    }

    // 멤버 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable("memberId") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    public MemberDto.response response(Member member) {
        return mapper.memberToMemberResponse(member);
    }

}
