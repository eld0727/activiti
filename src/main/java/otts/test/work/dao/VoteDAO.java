package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import otts.test.work.dbo.Vote;

import java.util.List;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link Vote} DAO
 */
public interface VoteDAO extends JpaRepository<Vote, Long> {

    /**
     * Gets by process id
     */
    Vote getByProcessId(String processId);

    /**
     * Gets all vote for certain owner
     */
    @Query("" +
            "select distinct v " +
            "from VoteParticipant vp" +
            " inner join vp.vote v" +
            " where vp.user = :user or v.owner = :user"
    )
    List<Vote> findAvailableForUser(@Param("user") String user);
}
