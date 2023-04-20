package shop.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
//이렇게 엔티티를 protected 해줌으로써 외부에서 new로 객체 생성을 막아 set을 막을 수 있고,  밑의 생성 메서드를 사용하게끔 할 수 있다.
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //order와 멤버는 다대일 관계
    //order의 입장에서 다대일
    //연관관계 매핑하기 - 연관관계의 주인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    //cascade : entity의 상태변화를 전파시키는 옵션
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    //1대 1관계는 fk를 아무데나 넣어도됨.
    //주로 접근을 많이 하는 곳에 넣는게 좋으므로 주문을 연관관계 주인으로 결정
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보

    //테이블 생성시 orderDate -> order_date로 변경됨
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]


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


    //==생성 메서드 ==//
    // -> new로 객체를 생성해 set하는 방식이 아니라 생성할때부터 이 메소드를 호출하도록해 한번에 생성되도록 해야함
    public static Order createOrder(Member member, Delivery delivery,
                                    OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }


    //==비즈니스 로직==//
    //주문 취소
    public void cancel() {
        //이미 배송완료라면
        if (delivery.getStatus() == DeliveryStatus.CAMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        //주문 상태 변경
        this.setStatus(OrderStatus.CANCEL);

        //고객을 한번 주문을할때 제품을 2개 주문을 할수있으니까 취소하면 재고를 다시 돌려놔야함.
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }

    }

    //==조회 로직==//
    //전체 주문 가격 조회
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }


}
