package lt.springinaction.tacocloud.repository;

import lt.springinaction.tacocloud.tacos.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
