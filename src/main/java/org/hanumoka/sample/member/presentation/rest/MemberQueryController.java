package org.hanumoka.sample.member.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.member.application.service.MemberQueryServiceImpl;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberQueryController {
    private final MemberQueryServiceImpl memberQueryServiceImpl;

    //멤버 단일 조회
//    @GetMapping("/{memberId}")
//    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
//        Member member = memberQueryServiceImpl.getMember(memberId);
//
//        MemberResponseDto memberResponseDto = MemberResponseDto.from(member);
//
//        return ResponseEntity.ok(memberResponseDto);
//    }

    //멤버 전체 조회 + 페이징
//    @GetMapping("/get-all-member")
//    public ResponseEntity<Page<MemberResponseDto>> getAllMember(@PageableDefault(size = 10, sort = "id") Pageable pageable) {
//
//        Page<Member> memberEntityPage = memberQueryServiceImpl.getMemberAll(pageable);
//
//        Page<MemberResponseDto> result = memberEntityPage.map(member -> MemberResponseDto.from(member));
//
//        return ResponseEntity.ok(result);
//    }
}
