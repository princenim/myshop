package shop.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hazel
 */

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String name;
    //내장타입을 포함했다라는 의미
    @Embedded
    private Address address;

    //member의 입장에서 리스트는 하나의 회원이 여러개의 주문을 하기 때문에 1대다 관계
    //mappedBy는 연관관계의 주인이 아닐 떄적음, 해당 필드 이름을 적아야함.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList();

}
