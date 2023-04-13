package shop.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.shop.domain.Item.Item;
import shop.shop.repository.ItemRepository;

import java.util.List;

/**
 * @author hazel
 */

@Service
@Transactional(readOnly = true) //디폴트로 설정
@RequiredArgsConstructor //파이널이 붙은 필드의 생성자를 자동 생성해주는 어노테이션
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
