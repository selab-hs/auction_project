package com.selab.auction.learning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordEncoderTest {
    @Test
    public void 패스워드_암호화() throws Exception {
        //given
        String password = "a1234";

        //when
        //String encodePassword = passwordEncoder.encode(password);

        //then
/*        assertThat(encodePassword).startsWith("{");
        assertThat(encodePassword).contains("{bcrypt}");
        assertThat(encodePassword).isNotEqualTo(password);*/
    }
}
