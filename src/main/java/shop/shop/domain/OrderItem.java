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
    @Id @GeneratedValue
    @Column(name ="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String orderPrice; //주문가격
    private Integer count; //주문 수량


}
