package shop.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.shop.domain.Item.Item;
import shop.shop.domain.Member;
import shop.shop.domain.Order;
import shop.shop.repository.OrderSearch;
import shop.shop.service.ItemService;
import shop.shop.service.MemberService;
import shop.shop.service.OrderService;

import java.util.List;

/**
 * @author hazel
 */
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 상품 주문 form
     *
     * @param model
     * @return
     */
    @GetMapping(value = "/order")
    public String createForm(Model model) {
        //모든 멤버와 아이템을 다 가져와서 모델에 넘긴다.
        List<Member> members = memberService.findMember();
        List<Item> items = itemService.findItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderForm";
    }


    /**
     * 상품 주문
     *
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }


    /**
     * 주문 내역
     *
     * @param orderSearch
     * @param model
     * @return
     */
    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch
                                    orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);

        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    /**
     * 주문 취소
     *
     * @param orderId
     * @return
     */

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }


}
