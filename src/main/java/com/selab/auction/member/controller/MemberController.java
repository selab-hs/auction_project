package com.selab.auction.member.controller;

import com.selab.auction.common.dto.ResponseDto;
import com.selab.auction.member.model.dto.*;
import com.selab.auction.member.service.MemberFindService;
import com.selab.auction.member.service.MemberSignUpService;
import com.selab.auction.member.signin.service.MemberSignInService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
// TODO : SWAGGER 작용하기
@RestController
@RequestMapping("/api/v1/auction")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSignUpService memberSignUpService;
    private final MemberFindService memberFindService;
    private final MemberSignInService userService;


    @PostMapping("/sign-up")
    public ResponseEntity<MemberSignUpResponseDto> signUp(@Valid @RequestBody MemberSignUpRequestDto newMember) {
        MemberSignUpResponseDto member = memberSignUpService.signUp(newMember);

        return ResponseDto.created(member);
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "회원 정보 조회")
    public ResponseEntity<MemberFindResponseDto> findMember(@PathVariable Long memberId) {
        MemberFindResponseDto findMember = memberFindService.findById(memberId);
        return ResponseDto.ok(findMember);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody MemberSignInRequestDto memberSignInRequestDto) {
        String token = userService.handleSignIn(memberSignInRequestDto);
        log.info(token);
        return ResponseEntity.ok(new MemberSignInResponseDto(token));
    }
}
