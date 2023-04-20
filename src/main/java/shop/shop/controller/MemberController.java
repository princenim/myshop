package shop.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.shop.domain.Address;
import shop.shop.domain.Member;
import shop.shop.service.MemberService;

/**
 * @author hazel
 */

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){

        //controller에서 view로 넘어갈때 이 데이터를 같이 넘김
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    //entity를 사용하지 말고 화면에 필요한 memberform을 만들기!
    public String create(@Valid MemberForm form, BindingResult result){

        //오류가 생기면 result에 담김 .

        if(result.hasErrors()){
            return "members/createMemberForm";

        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        //가입
        memberService.join(member);

        //홈으로 리다이렉트
        return "redirect:/";
    }


}
