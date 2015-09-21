package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otts.test.work.dbo.User;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link User} DAO
 */
public interface UserDAO extends JpaRepository<User, String> {
}
