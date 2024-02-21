package lt.springinaction.tacocloud.repository;


import lt.springinaction.tacocloud.tacos.TacoOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

//    TacoOrder save(TacoOrder order);
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> readByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}
