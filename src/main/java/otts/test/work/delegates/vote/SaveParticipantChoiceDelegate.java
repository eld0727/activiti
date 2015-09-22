package otts.test.work.delegates.vote;

import lombok.extern.log4j.Log4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import otts.test.work.dao.VoteOptionDAO;
import otts.test.work.dao.VoteParticipantDAO;
import otts.test.work.dbo.VoteOption;
import otts.test.work.dbo.VoteParticipant;
import otts.test.work.dto.VoteChoiceDTO;

/**
 * Created by alex on 20.09.2015.<br/>
 * Delegate saving participant vote choice
 */
@Log4j
@Component
public class SaveParticipantChoiceDelegate implements JavaDelegate {

    @Autowired
    private VoteParticipantDAO voteParticipantDAO;

    @Autowired
    private VoteOptionDAO voteOptionDAO;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String participant = (String) execution.getVariable("participant");
        VoteChoiceDTO option = (VoteChoiceDTO) execution.getVariable("voteChoice");
        VoteOption voteOption = voteOptionDAO.getOne(option.getOption());
        VoteParticipant voteParticipant = voteParticipantDAO.getByVoteAndUser(voteOption.getVote(), participant);
        voteParticipant.setVoteOption(voteOption);
    }
}
