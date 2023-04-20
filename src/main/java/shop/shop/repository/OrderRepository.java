package shop.shop.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import shop.shop.domain.Order;

import java.util.List;

/**
 * @author hazel
 */
@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //검색 로직 - 동적 쿼리 -> queryDsl로 처리 가능.
    //검색 조건 파라미터가 orderSearch
    public List<Order> findAll(OrderSearch orderSearch) {
        //order와 연관된 member를 조인 -> 실제로 sql join으로 쿼리가 실행됨.


        //후에 queryDsl로 수정 예정


        return null;
    }
}
