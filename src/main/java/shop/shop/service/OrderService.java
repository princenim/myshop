package shop.shop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.shop.domain.Delivery;
import shop.shop.domain.Item.Item;
import shop.shop.domain.Member;
import shop.shop.domain.Order;
import shop.shop.domain.OrderItem;
import shop.shop.repository.ItemRepository;
import shop.shop.repository.MemberRepository;
import shop.shop.repository.OrderRepository;

import java.util.List;

/**
 * @author hazel
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 주문
     */

    public Long order(Long memebrId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memebrId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        //왜 orderItem과 delivery가 있는데도 order만 save할까? 그 이유는 cascade = CascadeType.ALL로 상태변화를 전이시켰기때문
        //즉, orderItem도 save 하고 , delivery도 save 시킴
        orderRepository.save(order);

        return order.getId();
    }


    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();


        //jpa의 강점 - 변경 내역 감지라고 엔티티 안의 데이터를 바꾸면 Jpa가 감지(더티 체킹)를 해 알아서 업데이트 쿼리를 날려준다.


    }


    /**
     * 주문 검색
     */
//     public List<Order> findOrders(OrderSearch orderSearch){
//         return orderRepository.findAll(orderSearch);
//     }


}