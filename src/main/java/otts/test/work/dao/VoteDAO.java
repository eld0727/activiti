package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otts.test.work.dbo.Vote;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link Vote} DAO
 */
public interface VoteDAO extends JpaRepository<Vote, Long> {

    /**
     * Gets by process id
     */
    Vote getByProcessId(String processId);
}
