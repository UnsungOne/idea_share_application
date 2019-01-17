package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> fetchAllIdeas() {
        return ideaRepository.findAll();
    }

    public void addIdea(IdeaDTO ideaDTO) {
        ideaDTO.setAddedAt(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }

}