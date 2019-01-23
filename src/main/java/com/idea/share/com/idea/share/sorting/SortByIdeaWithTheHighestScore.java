package com.idea.share.com.idea.share.sorting;

import org.springframework.data.domain.Sort;

public class SortByIdeaWithTheHighestScore implements Sorting {
    @Override
    public void apply(Sort sort) {

        Sort ideaWithTheHighestScore = new Sort(Sort.Direction.DESC, "added");

    }
}
