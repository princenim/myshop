package shop.shop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import shop.shop.domain.Item.Item;

import java.util.List;

/**
 * @author hazel
 */
@Repository
//@RequiredArgsConstructor
public class ItemRepository {

    @PersistenceContext
    //private final EntityManager em;
    EntityManager em;

//    public void save(Item item){
//        if(item.getId() == null){
//            //아이템 저장 - 처음에 아이템은 jpa를 저장하기 전까지  id가 없음 -> 새로 생성한 객체라는 뜻
//            em.persist(item);
//        }else {
//            //이미 db에 있음.
//            em.merge(item);
//        }
//    }

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }


}
