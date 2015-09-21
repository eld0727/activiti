package otts.test.work.service;

import otts.test.work.dbo.Vote;
import otts.test.work.dto.VoteChoiceDTO;
import otts.test.work.dto.VoteCreateDTO;

/**
 * Created by alex on 20.09.2015.<br/>
 * Vote service
 */
public interface VoteService {

    /**
     * Creates a new {@link Vote}
     * @return new {@link Vote}
     */
    Vote createVote(VoteCreateDTO voteCreateDTO);

    /**
     * Participant make a choice
     */
    void participantMakeChoice(VoteChoiceDTO voteChoiceDTO);

    /**
     * Finish the vote
     * @return finished vote
     */
    Vote finishVote(long voteId);
}
