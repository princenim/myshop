package shop.shop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.shop.domain.Member;
import shop.shop.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author hazel
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional //테스트케이스에있으면 기본적으로 롤백을 해버림 따라서// Insert쿼리가 안 보임
        //spring이 롤백을 하면 jpa입장에서는 쿼리를 날릴 이유가 없음. 따라서 insert문이 없는것
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //@Rollback(value = false) //그럼에도 불구하고 db의 쿼리를 보고싶다면 추가
    public void 회원가입() throws Exception {
        //given - 주어질때
        Member member = new Member();
        member.setName("kim");
        //when - 이렇게 하면
        Long saveId = memberService.join(member);

        //then - 이렇게 된다.
        //이 멤버와 멤버 repository에서 id로 찾아온 멤버가 갈을때 같은 회원. 즉 가입이 정상적
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); // 여기서 예외가 터져야함
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생해야 한다");

    }


}