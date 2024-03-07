package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
