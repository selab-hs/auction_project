package com.selab.auction.learning;

import com.selab.auction.member.service.MemberSignUpService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private MemberSignUpService memberSignUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("비밀번호 암호화 테스트")
    void passwordEncode() {
        //given
        String password = "a1234";

        //when
        String encodedPassword = passwordEncoder.encode(password);

        //then
        assertAll(
                () -> assertNotEquals(password, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(password, encodedPassword))
        );
    }
}