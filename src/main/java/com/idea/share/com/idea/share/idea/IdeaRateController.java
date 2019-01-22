package com.idea.share.com.idea.share.idea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class IdeaRateController {

    private final IdeaService ideaService;

    @Autowired
    public IdeaRateController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @PostMapping(value = "/idea/{id}/rateUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaRateDTO> rateIdeaUp(IdeaRateDTO ideaRateDTO, @PathVariable Integer id) throws Exception {
        ideaService.rateIdeaUp(id);
        IdeaRateDTO currentIdeaDto = ideaService.getIdeaById(id);
        ideaRateDTO.setScore(currentIdeaDto.getScore());
        return new ResponseEntity<>(ideaRateDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/idea/{id}/rateDown", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaRateDTO> rateIdeaDown(IdeaRateDTO ideaRateDTO, @PathVariable Integer id) throws Exception {
        ideaService.rateIdeaDown(id);
        IdeaRateDTO currentIdeaDto = ideaService.getIdeaById(id);
        ideaRateDTO.setScore(currentIdeaDto.getScore());
        return new ResponseEntity<>(ideaRateDTO, HttpStatus.OK);
    }
}