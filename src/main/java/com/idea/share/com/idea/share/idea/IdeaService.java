package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.sorting.SortEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public Idea getIdeaById(Integer id) throws Exception {
        return ideaRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + id));
    }


    public Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase) {
        Sort sort;
        sort = getOrders(sortPhrase);
        Page<Idea> ideaEntities = ideaRepository.findAll(PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::maoToDTO);
        return ideaDTO;
    }

    public Sort getOrders(SortEnum sortPhrase) {
        Sort sort;
        if (sortPhrase.equals(SortEnum.ADDED)) {
            sort = new Sort(Sort.Direction.DESC, "addedAt");
        } else if (sortPhrase.equals(SortEnum.SCORE)) {
            sort = new Sort(Sort.Direction.DESC, "score");
        } else {
            sort = new Sort(Sort.Direction.DESC, "addedAt");
        }
        return sort;
    }

    public void addIdea(IdeaDTO ideaDTO) {
        ideaDTO.setAddedAt(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }

    public int rateIdeaUp(IdeaDTO ideaDTO, Integer ideaId) {
        return ideaRepository.rateIdeaUp(ideaId);
    }

    public int rateIdeaDown(Idea ideaDTO, Integer ideaId) {
        ideaDTO.getTitle();
   return ideaDTO.getScore() > 0 ? ideaRepository.rateIdeaDown(ideaId) : 0;
      //  return ideaRepository.rateIdeaDown(ideaId);
    }

    public void editIdea() {
    }

}