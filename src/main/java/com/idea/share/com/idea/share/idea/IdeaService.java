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
                .map(ModelMapper::maoToRateDTO)
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

    public void addIdea(IdeaDTO ideaDTO, HttpSession session) throws Exception {
        ideaDTO.setAddedAt(LocalDateTime.now());
        ideaDTO.setScore(0);
        ideaDTO.setUser(userService.findUserById((determinieUserId(session))));
        ideaRepository.save(ModelMapper.map(ideaDTO));
    }

    public int rateIdeaUp(Integer ideaId) {
        return ideaRepository.rateIdeaUp(ideaId);
    }

    public int rateIdeaDown(Integer ideaId) {
        return ideaRepository.rateIdeaDown(ideaId);
    }

    public void editIdea() {
    }


    public int determinieUserId(HttpSession session) {
        if (session.getAttribute("userid") != null) {
            return (int) session.getAttribute("userid");
        } else {
            return 0;
        }
    }
}