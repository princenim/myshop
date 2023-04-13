package shop.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import shop.shop.domain.Member;

import java.util.List;

/**
 * @author hazel
 */

@Repository
//자동으로 spring bean으로 관리됨
//component scan의 대상이 됨
public class MemberRepository {
    @PersistenceContext
    //엔티티를 관리하는 역할
    private EntityManager em;


    //jpa가 알아서 저장
    public void save(Member member) {
        em.persist(member);
    }

    //id로 조회
    public Member findOne(Long id) {
        //id 값으로 멤버를 찾아서 반환
        return em.find(Member.class, id);
    }

    //전체 리스트 조회
    public List<Member> findAll() {
        //jpql을 사용해서 전체 리스트 반환
        //jpql은 sql과 다름 - sql은 테이블을 대상으로 쿼리 , jpql는 엔티티 객체를 대상으로 쿼리
        //뒤에는 조회 타입
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //회원이름으로 검색 조회
    public List<Member> findMyName(String name) {
        return em.createQuery("select m from Member m where m.name :name", Member.class).
                setParameter("name", name).getResultList();

    }

}
