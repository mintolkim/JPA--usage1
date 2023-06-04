package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush(); // DB에 쿼리나가는 걸 보고 rollback 하고 싶다면 flush 사용
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복예외처리() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when (JUnit5버전)
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); // 예외가 발생해야한다!!
        });

        /*
        try {
            memberService.join(member2); // 예외가 발생해야한다!!
        } catch (IllegalStateException e){
            return;
        }
        //then
        fail("에외가 발생해야한다.");

         */



    }
}
