package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserService userService;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository, UserService userService) {
        this.ideaRepository = ideaRepository;
        this.userService = userService;
    }

    public IdeaRateDTO getIdeaById(Integer id) throws Exception {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::maoToRateDTOFromIdea)
                .orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + id));
    }

    public IdeaEditDTO findIdeaById(Integer id) throws Exception {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::mapToIdeaEditDTOFromIdea)
                .orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + id));
    }

    public Page<IdeaDTO> fetchMyIdeas(int user, int page, int limit, SortEnum sortPhrase, HttpSession session) {
        Sort sort = getOrders(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchUserIdeas(user, PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
        return ideaDTO;
    }

    public Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase, HttpSession session) {
        Sort sort = getOrders(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchAll(PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
        return ideaDTO;
    }

    public Sort getOrders(SortEnum sortPhrase, HttpSession session) {
        if (sortPhrase.equals(SortEnum.ADDED)) {
            session.setAttribute("sort", SortEnum.ADDED);
            return new Sort(Sort.Direction.DESC, "added");
        } else if (sortPhrase.equals(SortEnum.SCORE)) {
            session.setAttribute("sort", SortEnum.SCORE);
            return new Sort(Sort.Direction.DESC, "score");
        } else {
            session.setAttribute("sort", new Sort(Sort.Direction.DESC, "added"));
            return new Sort(Sort.Direction.DESC, "added");
        }
    }

    public void addIdea(Idea idea, int userId) throws Exception {
        idea.setAdded(LocalDateTime.now());
        idea.setScore(0);
        idea.setActive(true);
        idea.setUser(userService.findUserById(userId));
        ideaRepository.save((idea));
    }

    public void updateExistingIdea(String title, String description, int id) throws Exception {
        ideaRepository.updateExistingIdea(title, description, id);
    }

    public void rateIdeaUp(Integer ideaId) {
        ideaRepository.rateIdeaUp(ideaId);
    }


    public void rateIdeaDown(Integer ideaId) {
        ideaRepository.rateIdeaDown(ideaId);
    }
}
