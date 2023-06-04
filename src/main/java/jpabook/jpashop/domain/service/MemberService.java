package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA 로직이나 데이터 변경은 무조건 트랙잭션(스프링프레임워크) 안에서
// @Transactional(readOnly = true) -> 읽어올때는 이를 해주면 성능이 좋아짐
// @AllArgsConstructor 대신 constructor르 만들어준다 (ex1.) \
@RequiredArgsConstructor // final 만 가지고 밑에 ex1. 를 만듬 final 하는 걸 선호
public class MemberService {
    // @Autowired 를 하면 단점이 많음. 바꾸지 못함
    private final MemberRepository memberRepository; // final로 하는 걸 권장

    /*  ex1.) @AllArgsConstructor 를 쓰면 이를 대신 만들어준다.
        setter 대신 생성자를 쓴다
        @Autowired => 요즘은 생성자 생성시에 하나 있으면 자동으로 해준다
        public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        }
     */

    /*
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*
     * 회원 전체 목록 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
