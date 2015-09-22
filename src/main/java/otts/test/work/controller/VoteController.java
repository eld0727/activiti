package otts.test.work.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import otts.test.work.dto.UserDTO;
import otts.test.work.dto.VoteChoiceDTO;
import otts.test.work.dto.VoteCreateDTO;
import otts.test.work.dto.VoteDTO;
import otts.test.work.service.UserService;
import otts.test.work.service.VoteService;

import java.util.List;

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
    public VoteDTO createVote(@RequestBody VoteCreateDTO voteCreateDTO) {
        return voteService.createVote(voteCreateDTO);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public VoteDTO makeChoice(@RequestBody VoteChoiceDTO voteChoiceDTO) {
        return voteService.participantMakeChoice(voteChoiceDTO);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<VoteDTO> getAll() {
        return voteService.getAllForCurrentUser();
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public VoteDTO getById(
            @PathVariable Long id
    ) {
        return voteService.getVote(id);
    }

}
