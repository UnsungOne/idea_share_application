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

    public Page<IdeaDTO> fetchAllIdeas(int page, int limit, SortEnum sortPhrase) {
        Sort sort = getOrders(sortPhrase);
        Page<Idea> ideaEntities = ideaRepository.fetchAll(PageRequest.of(page, limit, sort));
        Page<IdeaDTO> ideaDTO = ideaEntities.map(ModelMapper::maoToDTO);
        return ideaDTO;
    }

    public Sort getOrders(SortEnum sortPhrase) {
        if (sortPhrase.equals(SortEnum.ADDED)) {
            return new Sort(Sort.Direction.DESC, "added");
        } else if (sortPhrase.equals(SortEnum.SCORE)) {
            return new Sort(Sort.Direction.DESC, "score");
        } else {
            return new Sort(Sort.Direction.DESC, "added");
        }
    }

    public void addIdea(IdeaDTO ideaDTO, HttpSession session) throws Exception {
        ideaDTO.setAdded(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaDTO.setActive(true);
        ideaDTO.setUser(userService.findUserById((determinieUserId(session))));
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }

    public int rateIdeaUp(Integer ideaId) {
        return ideaRepository.rateIdeaUp(ideaId);
    }

    public int rateIdeaDown(Integer ideaId) {
        return ideaRepository.rateIdeaDown(ideaId);
    }

    public List<Idea> canEditSelectedIdeas(HttpSession session) {
//        if (ideaRepository.fetchData().stream()
//                .allMatch(idea -> idea.getUser().getId() == determinieUserId(session))) return true;
//        else return false;

        List<Idea> nonEditableIdeas = new ArrayList<>();
        for (Idea idea : ideaRepository.fetchData()) {
            if (idea.getUser().getId() == determinieUserId(session)) {
                nonEditableIdeas.add(idea);
            }
            System.out.println(nonEditableIdeas);

//        return nonEditableIdeas;
//       Idea
//                .filter(idea -> idea.getUser().getId() == determinieUserId(session)) return true;
//        else return false;
//
//

        }
        return nonEditableIdeas;

    }


    public int determinieUserId(HttpSession session) {
        if (session.getAttribute("userid") != null) {
            return (int) session.getAttribute("userid");
        } else {
            return 0;
        }
    }


}