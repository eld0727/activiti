package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.dto.VoteChoiceDTO;
import otts.test.work.dto.VoteCreateDTO;
import otts.test.work.service.VoteService;

/**
 * Created by alex on 20.09.2015.<br/>
 * Vote controller
 */
@Controller
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public Long createVote(@RequestBody VoteCreateDTO voteCreateDTO) {
        return voteService.createVote(voteCreateDTO).getId();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void makeChoice(@RequestBody VoteChoiceDTO voteChoiceDTO) {
        voteService.participantMakeChoice(voteChoiceDTO);
    }

}
