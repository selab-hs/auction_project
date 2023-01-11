package com.selab.auction.member.controller;

import com.selab.auction.common.dto.ResponseDto;
import com.selab.auction.member.auth.service.MemberSignInService;
import com.selab.auction.member.model.dto.*;
import com.selab.auction.member.service.MemberFindService;
import com.selab.auction.member.service.MemberSignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
// TODO : SWAGGER 적용하기
@RestController
@RequestMapping("/api/v1/auction")
@RequiredArgsConstructor
public class MemberController {

    private final MemberSignUpService memberSignUpService;
    private final MemberFindService memberFindService;
    private final MemberSignInService memberSignInService;

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity<MemberSignUpResponseDto> signUp(@Valid @RequestBody MemberSignUpRequestDto newMember) {
        MemberSignUpResponseDto member = memberSignUpService.signUp(newMember);

        return ResponseDto.created(member);
    }

    @GetMapping("/{memberId}")
    @ApiOperation(value = "회원 정보 조회")
    // TODO: 회원정보 조회 시 ADMIN 계정과 해당 회원만 볼 수 있도록 하기
    public ResponseEntity<MemberFindResponseDto> findMember(@PathVariable Long memberId) {
        MemberFindResponseDto findMember = memberFindService.findById(memberId);

        return ResponseDto.ok(findMember);
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인")
    public ResponseEntity<MemberSignInResponseDto> signIn(
            @Valid @RequestBody MemberSignInRequestDto memberSignInRequestDto) {
        memberSignInService.validateSignIn(memberSignInRequestDto);

        Authentication authentication = memberSignInService.getAuth(memberSignInRequestDto);
        String accessToken = memberSignInService.getAccessToken(authentication);
        String refreshToken = memberSignInService.getRefreshToken(authentication);

        return ResponseDto.ok(new MemberSignInResponseDto(accessToken, refreshToken));
    }
}
