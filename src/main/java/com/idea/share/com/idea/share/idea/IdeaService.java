package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.idea.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {

    private IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> fetchAllIdeas(){
       return ideaRepository.findAll();
    }
}