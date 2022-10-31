package com.selab.auction.member.controller;

import com.selab.auction.member.dto.MemberSignUpRequestDto;
import com.selab.auction.member.dto.MemberSignUpResponseDto;
import com.selab.auction.member.service.MemberSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auction")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSignUpService memberSignUpService;

    @PostMapping("/singUp")
    public ResponseEntity<MemberSignUpResponseDto> signUp(@Valid @RequestBody MemberSignUpRequestDto newMember) {
        MemberSignUpResponseDto member = memberSignUpService.signUp(newMember);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }
}
