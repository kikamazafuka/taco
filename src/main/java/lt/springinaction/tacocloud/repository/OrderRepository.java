package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

//    TacoOrder save(TacoOrder order);
}
