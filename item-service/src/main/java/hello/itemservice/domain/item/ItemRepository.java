package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

  private static final Map<Long, Item> store = new HashMap<>(); // 실제는 hashmap 사용 안함
  private static long sequence = 0L;

  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    return item;
  }

  public Item findById(Long id) {
    return store.get(id);
  }

  public List<Item> findAll() {
    return new ArrayList<>(store.values()); // store을 보호하기 위해 새로 반환한다.
  }

  public void update(Long itemId, Item updateParam) {
    Item findItem = findById(itemId);
    findItem.setItemName(updateParam.getItemName());
    findItem.setPrice(updateParam.getPrice());
    findItem.setQuantity(updateParam.getQuantity());
  }

  public void clearStore() {
    store.clear();
  }
}
