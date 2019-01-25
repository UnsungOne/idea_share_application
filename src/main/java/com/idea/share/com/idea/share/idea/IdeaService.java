package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.sorting.SortEnum;
import com.idea.share.com.idea.share.user.User;
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
import java.util.Set;
import java.util.stream.Collectors;

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
                .map(ModelMapper::maoToRateDTO)
                .orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + id));
    }


    public Page<IdeaDTO> fetchMyIdeas(int user, int page, int limit, SortEnum sortPhrase, HttpSession session) {
        Sort sort = getOrders(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchUserIdeas(user, PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::maoToDTO);
        return ideaDTO;
    }


    public Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase, HttpSession session) {
        Sort sort = getOrders(sortPhrase, session);
        Page<Idea> ideaEntities = ideaRepository.fetchAll(PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::maoToDTO);
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

    public void updateExistingIdea(String title, String description, int IdeaId) {
        ideaRepository.updateExistingIdea(title, description, IdeaId);
    }


    public int rateIdeaUp(Integer ideaId) {
        return ideaRepository.rateIdeaUp(ideaId);
    }

    public int rateIdeaDown(Integer ideaId) {
        return ideaRepository.rateIdeaDown(ideaId);
    }

//    public boolean canEditSelectedIdeas(int id) {
//        if (ideaRepository.fetchData().getId()==id)){
//          return true;
//        } else return false;
//
//    }

//    public List<Idea> canEditSelectedIdeas1(HttpSession session) {
//        if (ideaRepository.fetchData().stream()
//                .allMatch(idea -> idea.getUser().getId() == determinieUserId(session))) return true;
//        else return false;
//
//        List<Idea> nonEditableIdeas = new ArrayList<>();
//        for (Idea idea : ideaRepository.fetchData()) {
//            if (idea.getUser().getId() == determinieUserId(session)) {
//                nonEditableIdeas.add(idea);
//            }
//            System.out.println(nonEditableIdeas.toString());

//        return nonEditableIdeas;
//       Idea
//                .filter(idea -> idea.getUser().getId() == determinieUserId(session)) return true;
//        else return false;
//
//

//        }
//        return nonEditableIdeas;
//
//    }


}