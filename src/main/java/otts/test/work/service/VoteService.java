package otts.test.work.service;

import otts.test.work.dbo.Vote;
import otts.test.work.dto.VoteChoiceDTO;
import otts.test.work.dto.VoteCreateDTO;
import otts.test.work.dto.VoteDTO;

import java.util.List;

/**
 * Created by alex on 20.09.2015.<br/>
 * Vote service
 */
public interface VoteService {

    /**
     * Creates a new {@link Vote}
     * @return new {@link Vote}
     */
    VoteDTO createVote(VoteCreateDTO voteCreateDTO);

    /**
     * Participant make a choice
     */
    VoteDTO participantMakeChoice(VoteChoiceDTO voteChoiceDTO);

    /**
     * Finish the vote
     * @return finished vote
     */
    Vote finishVote(long voteId);

    /**
     * Gets all votes available for current user
     */
    List<VoteDTO> getAllForCurrentUser();

    /**
     * Gets vote by id
     */
    VoteDTO getVote(Long id);
}
