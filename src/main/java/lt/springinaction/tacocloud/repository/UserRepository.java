package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
