package otts.test.work.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otts.test.work.dbo.Vote;
import otts.test.work.dbo.VoteParticipant;

/**
 * Created by alex on 19.09.2015.<br/>
 * {@link otts.test.work.dbo.VoteParticipant} DAO
 */
public interface VoteParticipantDAO extends JpaRepository<VoteParticipant, Long> {

    /**
     * Gets vote participant by vote and user
     */
    VoteParticipant getByVoteAndUser(Vote vote, String user);
}
