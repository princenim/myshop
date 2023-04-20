package shop.shop.repository;

import lombok.Getter;
import lombok.Setter;
import shop.shop.domain.OrderStatus;

/**
 * @author hazel
 */

@Getter
@Setter
public class OrderSearch {
    private String memberName; //회원이름
    private OrderStatus orderStatus; //주문상태(ORDER,CANCEL)


}
