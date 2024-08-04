package org.hanumoka.sample.member.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.domain.vo.MemberCreate;
import org.hanumoka.sample.member.presentation.rest.request.CreateMemberRequestDto;
import org.hanumoka.sample.member.application.service.MemberCommandServiceImpl;
import org.hanumoka.sample.member.application.service.MemberQueryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberCommandController {
    private final MemberQueryServiceImpl memberQueryServiceImpl;
    private final MemberCommandServiceImpl memberCommandServiceImpl;
    
    //멤버 생성
    @PostMapping
    public ResponseEntity<Long> createMember(@RequestBody CreateMemberRequestDto request) {
        MemberCreate memberCreate = MemberCreate.builder()
                .username(request.getUsername())
                .name(request.getName())
                .build();
        Long memberId = memberCommandServiceImpl.createMember(memberCreate);
        return ResponseEntity.ok(memberId);
    }

    //멤버 삭제
    @PostMapping("/{memberId}/delete")
    public ResponseEntity<Long> deleteMember(@PathVariable Long memberId) {
        long deletedMemberId = memberCommandServiceImpl.deleteMember(memberId);
        return ResponseEntity.ok(deletedMemberId);
    }
    
}
