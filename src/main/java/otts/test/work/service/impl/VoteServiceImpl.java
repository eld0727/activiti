package otts.test.work.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otts.test.work.dao.VoteDAO;
import otts.test.work.dao.VoteOptionDAO;
import otts.test.work.dbo.Vote;
import otts.test.work.dbo.VoteOption;
import otts.test.work.dto.VoteChoiceDTO;
import otts.test.work.dto.VoteCreateDTO;
import otts.test.work.service.UserService;
import otts.test.work.service.VoteService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 20.09.2015.<br/>
 * Implementation of {@link VoteService}
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    private final static String VOTE_PROCESS_KEY = "voteProcess";

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private VoteDAO voteDAO;

    @Autowired
    private VoteOptionDAO voteOptionDAO;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Vote createVote(VoteCreateDTO voteCreateDTO) {
        voteCreateDTO.setOwner(userService.getCurrentUser().getId());
        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("voteDTO", voteCreateDTO);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(VOTE_PROCESS_KEY, processVariables);
        return voteDAO.getByProcessId(processInstance.getProcessInstanceId());
    }

    @Override
    public void participantMakeChoice(VoteChoiceDTO voteChoiceDTO) {
        voteChoiceDTO.setParticipant(userService.getCurrentUser().getId());
        VoteOption voteOption = voteOptionDAO.getOne(voteChoiceDTO.getOption());
        String processId = voteOption.getVote().getProcessId();
        Task task = taskService.createTaskQuery()
                .taskCandidateOrAssigned(voteChoiceDTO.getParticipant())
                .processInstanceId(processId)
                .singleResult();

        if(task != null) {
            Map<String, Object> taskVariables = new HashMap<>();
            taskVariables.put("voteChoice", voteChoiceDTO);
            taskService.complete(task.getId(), taskVariables);
        }
    }

    @Override
    public Vote finishVote(long voteId) {
        Vote vote = voteDAO.getOne(voteId);
        if(vote != null) {
            vote.setFinished(true);
        }
        return vote;
    }
}
