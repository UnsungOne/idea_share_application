package com.idea.share.com.idea.share.service;

import com.idea.share.com.idea.share.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdeaService {

    private IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }
}
