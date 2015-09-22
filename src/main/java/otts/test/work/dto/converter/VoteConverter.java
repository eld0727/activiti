package otts.test.work.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import otts.test.work.dbo.Vote;
import otts.test.work.dbo.VoteOption;
import otts.test.work.dbo.VoteParticipant;
import otts.test.work.dto.OptionDTO;
import otts.test.work.dto.VoteDTO;
import otts.test.work.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 22.09.2015.<br/>
 * Vote -> VoteDTO
 */
@Component
public class VoteConverter implements Converter<Vote, VoteDTO> {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;

    @Override
    public VoteDTO convert(Vote source) {
        String id = userService.getCurrentUser().getId();

        VoteDTO vote = new VoteDTO();
        vote.setId(source.getId());
        vote.setOwner(userConverter.convert(userService.byId(source.getOwner())));
        vote.setFinished(source.isFinished());
        vote.setText(source.getText());
        List<OptionDTO> options = new ArrayList<>();
        vote.setOptions(options);
        Map<Long, OptionDTO> opts = new HashMap<>();
        if(!CollectionUtils.isEmpty(source.getOptions())) {
            for (VoteOption voteOption : source.getOptions()) {
                OptionDTO optionDTO = new OptionDTO();
                optionDTO.setId(voteOption.getId());
                optionDTO.setText(voteOption.getText());
                options.add(optionDTO);
                opts.put(optionDTO.getId(), optionDTO);
            }
        }

        boolean currentUserVoted = false;
        if(!CollectionUtils.isEmpty(source.getParticipants())) {
            int size = 0;
            for (OptionDTO optionDTO : vote.getOptions()) {
                optionDTO.setCount(0);
            }
            for (VoteParticipant p : source.getParticipants()) {
                if(p.getVoteOption() == null) {
                    continue;
                }
                size++;
                if(id.equals(p.getUser())) {
                    currentUserVoted = true;
                }
                OptionDTO optionDTO = opts.get(p.getVoteOption().getId());
                optionDTO.setCount(optionDTO.getCount() + 1);
            }
            for (OptionDTO optionDTO : vote.getOptions()) {
                optionDTO.setPercent(Math.round(optionDTO.getCount().doubleValue() * 10000 / size) / 100.0);
            }
        }
        if(!currentUserVoted) {
            for (OptionDTO optionDTO : vote.getOptions()) {
                optionDTO.setCount(null);
                optionDTO.setPercent(null);
            }
        } else {
            vote.setShowResults(true);
        }
        return vote;
    }
}
