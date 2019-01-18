package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Page<IdeaDTO> fetchAllIdeas(int page, int limit) {
        Sort sortOnAddedAt = new Sort(Sort.Direction.DESC, "addedAt");
        Page<Idea> ideaEntities = ideaRepository.findAll(PageRequest.of(page, limit, sortOnAddedAt));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::map);
        return ideaDTO;
    }

    public void addIdea(IdeaDTO ideaDTO) {
        ideaDTO.setAddedAt(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }


    public void rateIdeaUp(Integer ideaId) {
        ideaRepository.rateIdeaUp(ideaId);
    }

    public void rateIdeaDown(Integer ideaId) {
        ideaRepository.rateIdeaDown(ideaId);

    }

    public void editIdea(){

    }

}