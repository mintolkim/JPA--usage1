package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em; // data-jpa 가 다 설정해줌

    public Long save(Member member){ //shift ctrl T -> test
        em.persist(member);
        return member.getId(); //커맨드랑 쿼리를 분리
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }



}
