package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.exception.IdeaNotFoundException;
import com.idea.share.com.idea.share.exception.UserNotFoundException;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdeaService {

    private IdeaRepository ideaRepository;
    private UserVotedIdeasRepository userVotedIdeasRepository;
    private UserService userService;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository, UserVotedIdeasRepository userVotedIdeasRepository, UserService userService) {
        this.ideaRepository = ideaRepository;
        this.userVotedIdeasRepository = userVotedIdeasRepository;
        this.userService = userService;

    }

    public IdeaRateDTO getIdeaById(Integer id) throws IdeaNotFoundException {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::maoToRateDTOFromIdea)
                .orElseThrow(() -> new IdeaNotFoundException("Nie znaleziono pomysłu o id: " + id));
    }

    public IdeaEditDTO findIdeaById(Integer id) throws IdeaNotFoundException {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::mapToIdeaEditDTOFromIdea)
                .orElseThrow(() -> new IdeaNotFoundException("Nie znaleziono pomysłu o id: " + id));
    }

    public Page<IdeaDTO> fetchMyIdeas(int user, int page, int limit, SortEnum sortPhrase, HttpSession session) {

        Sort sort = getSortingTypes(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchUserIdeas(user, PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
        return ideaDTO;
    }

    public Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase, HttpSession session) {
        int resultPage = page >= 0 ? page : 0;
        Sort sort = getSortingTypes(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchAllActiveIdeas(PageRequest.of(resultPage, limit, sort));
        return ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
    }

    public Sort getSortingTypes(SortEnum sortPhrase, HttpSession session) {
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

    public void updateExistingIdea(String title, String description, int id) {
        ideaRepository.updateExistingIdea(title, description, id);
    }

    public void rateIdeaUp(Integer ideaId) {
        ideaRepository.rateIdeaUp(ideaId);
    }


    public void rateIdeaDown(Integer ideaId) {
        ideaRepository.rateIdeaDown(ideaId);
    }

    public boolean determineIfUserIsAuthorOfGivenIdea(Integer userId, Integer ideaId) {
        List<Integer> userIdeas = ideaRepository.getIdeasCreatedByUser(userId)
                .stream()
                .map(Idea::getId)
                .collect(Collectors.toList());
        return userIdeas.contains(ideaId);
    }


    public void makeGivenIdeaVotedForUser(Integer userId, Integer ideaID) throws IdeaNotFoundException, UserNotFoundException {
        UserVotedIdeas userVotedIdeas = new UserVotedIdeas();
        List<Idea> allIdeas = new ArrayList<>();

        for (Idea idea : ideasVotedByUsers) {
            allIdeas.add(ideaRepository.findById(idea.getId()).orElseThrow(
                    () ->
                            new IdeaNotFoundException(
                                    "Nie znaleziono produktu o id: " + idea.getId())));
        }

        userVotedIdeas.setUser(userService.findUserById(userId));
        userVotedIdeas.setIdeas(allIdeas);
        userVotedIdeasRepository.save(userVotedIdeas);

    }
}