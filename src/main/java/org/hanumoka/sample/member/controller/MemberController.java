package org.hanumoka.sample.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.controller.request.CreateMemberRequestDto;
import org.hanumoka.sample.member.controller.response.MemberDTO;
import org.hanumoka.sample.member.controller.request.UpdateMemberRequestDto;
import org.hanumoka.sample.member.infra.MemberEntity;
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
        Long memberId = memberService.createMember(request.getUsername(), request.getName());
        return ResponseEntity.ok(memberId);
    }
    
    //멤버 단일 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long memberId) {
        MemberEntity member = memberService.getMember(memberId);

        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();

        return ResponseEntity.ok(memberDTO);
    }
    
    //멤버 수정
    @PostMapping("/{memberId}")
    public ResponseEntity<Long> updateMember(@PathVariable Long memberId, @RequestBody UpdateMemberRequestDto request) {
        long updatedMemberId = memberService.updateMember(memberId, request.getName());
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
    public ResponseEntity<Page<MemberDTO>> getAllMember(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

        Page<MemberEntity> memberEntityPage = memberService.getMemberAll(pageable);

        Page<MemberDTO> result = memberEntityPage.map(member -> MemberDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build());

        return ResponseEntity.ok(result);
    }
}
