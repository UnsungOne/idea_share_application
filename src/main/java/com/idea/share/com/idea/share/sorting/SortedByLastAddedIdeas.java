package com.idea.share.com.idea.share.sorting;

import org.springframework.data.domain.Sort;

public class SortedByLastAddedIdeas implements Sorting {


    public void apply(Sort sort) {

        Sort lastAddedIdeas = new Sort(Sort.Direction.DESC, "added");

    }
}

