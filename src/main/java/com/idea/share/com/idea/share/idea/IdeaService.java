package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<IdeaDTO> fetchAllIdeas(int page, int limit) {
        List<IdeaDTO> returnValue = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Idea> ideas = ideaRepository.findAll(pageableRequest);
        List<Idea> ideaEntities = ideas.getContent();
        for (Idea Idea : ideaEntities) {
            IdeaDTO userDto = new IdeaDTO();
            BeanUtils.copyProperties(Idea, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

    public void addIdea(IdeaDTO ideaDTO) {
        ideaDTO.setAddedAt(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }


//    public void rateIdeaUp(Integer ideaId) {
//        ideaRepository.rateIdeaUp(ideaId);
//    }
//
//    public void rateIdeaDown(Integer ideaId) {
//        ideaRepository.rateIdeaDown(ideaId);
//
//    }
}