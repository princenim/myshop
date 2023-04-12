package shop.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hazel
 */

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //order와 멤버는 다대일 관계
    //order의 입장에서 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    //연관관계 매핑하기 - 연관관계의 주인
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    //cascade : entity의 상태변화를 전파시키는 옵션
    private List<OrderItem> orderItems = new ArrayList<>();


    //1대 1관계는 fk를 아무데나 넣어도됨.ㅈ
    //주로 접근을 많이 하는 곳에 넣는게 좋으므로 주문을 연관관계 주인으로 결정
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //테이블 생성시 orderDate -> order_data로 변경됨
    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL] - enum


    //todo 연관관계 편의 메소드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);


    }


}
