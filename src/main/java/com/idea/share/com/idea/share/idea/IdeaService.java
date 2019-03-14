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

    IdeaRateDTO getIdeaById(Integer id) throws IdeaNotFoundException {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::maoToRateDTOFromIdea)
                .orElseThrow(() -> new IdeaNotFoundException("Nie znaleziono pomysłu o id: " + id));
    }

    IdeaEditDTO findIdeaById(Integer id) throws IdeaNotFoundException {
        return ideaRepository
                .findById(id)
                .map(ModelMapper::mapToIdeaEditDTOFromIdea)
                .orElseThrow(() -> new IdeaNotFoundException("Nie znaleziono pomysłu o id: " + id));
    }

    Page<IdeaDTO> fetchMyIdeas(int user, int page, int limit, SortEnum sortPhrase, HttpSession session) {

        Sort sort = getSortingTypes(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchUserIdeas(user, PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
        return ideaDTO;
    }

    Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase, HttpSession session) {
        int resultPage = page >= 0 ? page : 0;
        Sort sort = getSortingTypes(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchAllActiveIdeas(PageRequest.of(resultPage, limit, sort));
        return ideaEntities.map(ModelMapper::mapToIdeaDTOFromIdea);
    }

    private Sort getSortingTypes(SortEnum sortPhrase, HttpSession session) {
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

    void addIdea(Idea idea, int userId) throws Exception {
        idea.setAdded(LocalDateTime.now());
        idea.setScore(0);
        idea.setActive(true);
        idea.setUser(userService.findUserById(userId));
        ideaRepository.save((idea));
    }

    void updateExistingIdea(String title, String description, int id) {
        ideaRepository.updateExistingIdea(title, description, id);
    }

    void rateIdeaUp(Integer ideaId) {
        ideaRepository.rateIdeaUp(ideaId);
    }


    void rateIdeaDown(Integer ideaId) {
        ideaRepository.rateIdeaDown(ideaId);
    }

    boolean determineIfUserIsAuthorOfGivenIdea(Integer userId, Integer ideaId) {
        List<Integer> ideasCreatedByUser = ideaRepository.getIdeasCreatedByUser(userId)
                .stream()
                .map(Idea::getId)
                .collect(Collectors.toList());
        return ideasCreatedByUser.contains(ideaId);
    }


    void makeGivenIdeaVotedForUser(Integer userId, Integer ideaId) throws IdeaNotFoundException, UserNotFoundException {
        UserVotedIdeas userVotedIdeas = new UserVotedIdeas();
        userVotedIdeas.setUser(userService.findUserById(userId));
        userVotedIdeas.setIdea(ideaRepository.findById(ideaId).get());
        userVotedIdeasRepository.save(userVotedIdeas);

    }

    boolean determineIfUserVotedForAGivenIdea(Integer userId, Integer ideaID) {
        if (ideaRepository.getIdeasVotedByUser(userId).contains(ideaID)) {
            System.out.println("ERRRRRRRRRRRR");
            return true;
        } else {
            return false;
        }
    }
}