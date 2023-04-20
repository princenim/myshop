package shop.shop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.shop.domain.Item.Item;

/**
 * @author hazel
 */

@Entity
@Getter
@Setter
@Table(name = "order_item")
//이렇게 엔티티를 protected 해줌으로써 외부에서 new로 객체 생성을  막을 수 있고, 밑의 생성메서드를 사용하게끔 할 수 있다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; //주문

    private int orderPrice; //주문가격
    private int count; //주문 수량


    //==비즈니스 로직==//
    public void cancel() {
        //재고를 주문 수량만큼 늘려줘야함.
        getItem().addStock(count);
    }


    //주문할때 주문 가격과 수량이 존재하므로
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    //==생성 메서드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int
            count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

}
