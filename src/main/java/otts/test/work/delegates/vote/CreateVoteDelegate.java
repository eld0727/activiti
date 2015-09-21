package otts.test.work.delegates.vote;

import lombok.extern.log4j.Log4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import otts.test.work.dao.UserDAO;
import otts.test.work.dao.VoteDAO;
import otts.test.work.dao.VoteOptionDAO;
import otts.test.work.dao.VoteParticipantDAO;
import otts.test.work.dbo.Vote;
import otts.test.work.dbo.VoteOption;
import otts.test.work.dbo.VoteParticipant;
import otts.test.work.dto.VoteCreateDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 19.09.2015.<br/>
 * Delegate creating vote
 */
@Log4j
@Component
public class CreateVoteDelegate implements JavaDelegate {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private VoteDAO voteDAO;

    @Autowired
    private VoteOptionDAO voteOptionDAO;

    @Autowired
    private VoteParticipantDAO voteParticipantDAO;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        VoteCreateDTO voteCreateDTO = (VoteCreateDTO) execution.getVariable("voteDTO");
        Vote vote = new Vote();
        vote.setText(voteCreateDTO.getText());
        vote.setProcessId(execution.getProcessInstanceId());
        vote.setOwner(userDAO.getOne(voteCreateDTO.getOwner()));
        voteDAO.saveAndFlush(vote);

        for (String option : voteCreateDTO.getOptions()) {
            VoteOption voteOption = new VoteOption();
            voteOption.setVote(vote);
            voteOption.setText(option);
            voteOptionDAO.save(voteOption);
        }

        List<String> participants = new ArrayList<>(voteCreateDTO.getParticipants());
        if(!participants.contains(voteCreateDTO.getOwner())) {
            participants.add(voteCreateDTO.getOwner());
        }

        for (String participant : participants) {
            VoteParticipant voteParticipant = new VoteParticipant();
            voteParticipant.setUser(userDAO.getOne(participant));
            voteParticipant.setVote(vote);
            voteParticipantDAO.save(voteParticipant);
        }

        execution.setVariable("vote", vote);
        execution.setVariable("participants", participants);
    }
}
