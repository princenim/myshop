package shop.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.shop.domain.Member;
import shop.shop.repository.MemberRepository;

import java.util.List;

/**
 * @author hazel
 */

@Service
@Transactional(readOnly = true) //조회 시 기능이 더 좋음.-> 보통 읽기가 많으니 디폴트로 설정
//@RequiredArgsConstructor // final 필드를 가지고 생성자를 만들어줌. 따라서 저 밑에 생성자를 지워도 됨
public class MemberService {

    //의존성 주입 (필드 주입, setter 주입 등  종류가 다양하지만 생성자 주입이 가장 선호됨)
    //그리고 이제 수정할일이 없기때문에 final로 고정
    private final MemberRepository memberRepository;
    @Autowired // 최신 스프링에서는 생성자가 하나만 있는 경우 자동으로 autowired를 해준다. 따라서 지워도 ㄱㅊ
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //생성자 주입은 생성할떄 주입을 하기때문에 중간에 바꿀 수 없음.

    //1. 회원가입
    //우선권으로 디폴트보다 먼저 실행됨
    @Transactional
    public Long join(Member member) {
        //중복회원 검증
        validationDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validationDuplicateMember(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findMyName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //2. 회원 전체 조회
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    //3. 회원 id로 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
