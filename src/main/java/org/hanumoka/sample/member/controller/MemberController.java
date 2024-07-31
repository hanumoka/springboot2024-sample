package org.hanumoka.sample.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.controller.request.CreateMemberRequestDto;
import org.hanumoka.sample.member.controller.response.MemberResponseDto;
import org.hanumoka.sample.member.controller.request.UpdateMemberRequestDto;
import org.hanumoka.sample.member.domain.Member;
import org.hanumoka.sample.member.domain.MemberCreate;
import org.hanumoka.sample.member.domain.MemberUpdate;
import org.hanumoka.sample.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {
    private final MemberService memberService;
    
    //멤버 생성
    @PostMapping
    public ResponseEntity<Long> createMember(@RequestBody CreateMemberRequestDto request) {
        MemberCreate memberCreate = MemberCreate.builder()
                .username(request.getUsername())
                .name(request.getName())
                .build();
        Long memberId = memberService.createMember(memberCreate);
        return ResponseEntity.ok(memberId);
    }
    
    //멤버 단일 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMember(memberId);

        MemberResponseDto memberResponseDto = MemberResponseDto.from(member);

        return ResponseEntity.ok(memberResponseDto);
    }
    
    //멤버 수정
    @PostMapping("/{memberId}")
    public ResponseEntity<Long> updateMember(@PathVariable Long memberId, @RequestBody UpdateMemberRequestDto request) {
        MemberUpdate memberUpdate = MemberUpdate.builder()
                .id(memberId)
//                .username(request.getUsername())  // TODO: update 시 받지 않는 데이터는?
                .name(request.getName())
                .build();
        long updatedMemberId = memberService.updateMember(memberUpdate);
        return ResponseEntity.ok(updatedMemberId);
    }
    
    //멤버 삭제
    @PostMapping("/{memberId}/delete")
    public ResponseEntity<Long> deleteMember(@PathVariable Long memberId) {
        long deletedMemberId = memberService.deleteMember(memberId);
        return ResponseEntity.ok(deletedMemberId);
    }
    
    //멤버 전체 조회 + 페이징
    @GetMapping("/get-all-member")
    public ResponseEntity<Page<MemberResponseDto>> getAllMember(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<Member> memberEntityPage = memberService.getMemberAll(pageable);

        Page<MemberResponseDto> result = memberEntityPage.map(member -> MemberResponseDto.from(member));

        return ResponseEntity.ok(result);
    }
}
