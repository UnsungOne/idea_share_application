package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
public class IdeaRateController {

    private final IdeaService ideaService;
    private final UserService userService;

    @Autowired
    public IdeaRateController(IdeaService ideaService, UserService userService) {
        this.ideaService = ideaService;
        this.userService = userService;
    }

    @PostMapping(value = "/idea/rateUp/{ideaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaRateDTO> rateIdeaUp(Model model, IdeaRateDTO ideaRateDTO, @PathVariable Integer ideaId, HttpServletRequest request, @SessionAttribute User user) throws Exception {
        if (!userService.isEligibleToVote(user.getId(), request)) {
            if (!ideaService.determineIfUserIsAuthorOfGivenIdea(user.getId(), ideaId)) {
                ideaService.rateIdeaUp(ideaId);
                userService.makeUserAlreadyVoted(user.getId());
            }
        }
        IdeaRateDTO currentIdeaDto = ideaService.getIdeaById(ideaId);
        ideaRateDTO.setScore(currentIdeaDto.getScore());
        return new ResponseEntity<>(ideaRateDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/idea/rateDown/{ideaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaRateDTO> rateIdeaDown(IdeaRateDTO ideaRateDTO, @PathVariable Integer ideaId, HttpServletRequest request, @SessionAttribute User user) throws Exception {
        if (!userService.isEligibleToVote(user.getId(), request)) {
            if (!ideaService.determineIfUserIsAuthorOfGivenIdea(user.getId(), ideaId)) {gi
                ideaService.rateIdeaDown(ideaId);
                userService.makeUserAlreadyVoted(user.getId());
            }
        }

        IdeaRateDTO currentIdeaDto = ideaService.getIdeaById(ideaId);
        ideaRateDTO.setScore(currentIdeaDto.getScore());
        return new ResponseEntity<>(ideaRateDTO, HttpStatus.OK);
    }
}