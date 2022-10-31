package com.selab.auction.member.repository;

import com.selab.auction.member.model.entity.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
/*    @Autowired EntityManager em;

    @AfterEach
    private void after(){
        em.clear();
    }*/

    @Test
    public void 회원저장_성공() throws Exception {
        //given
        Member member = new Member(
                "test@test.com",
                "a1234",
                "test",
                "test",
                "010-1111-1111",
                "F"
        );

        //when
        Member saveMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository
                .findById(saveMember.getId())
                .orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));

        assertThat(findMember).isSameAs(saveMember);
        assertThat(findMember).isSameAs(member);
    }

}