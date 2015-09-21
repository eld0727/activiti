package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otts.test.work.dbo.VoteOption;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link otts.test.work.dbo.VoteOption} DAO
 */
public interface VoteOptionDAO extends JpaRepository<VoteOption, Long> {
}
