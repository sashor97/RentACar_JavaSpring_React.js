package proektwp.proektwp.repository;

import org.springframework.data.repository.CrudRepository;
import proektwp.proektwp.models.UserSecurity;

public interface UserSecurityRepository extends CrudRepository<UserSecurity, Long> {

    UserSecurity findByUsername(String username);

}
