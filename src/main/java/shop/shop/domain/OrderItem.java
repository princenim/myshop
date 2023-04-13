package shop.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.shop.domain.Item.Item;

/**
 * @author hazel
 */

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

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

    //생성메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //재고 차감
        item.removeStock(count);
        return orderItem;
    }

}
