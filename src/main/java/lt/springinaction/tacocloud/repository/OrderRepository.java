package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
