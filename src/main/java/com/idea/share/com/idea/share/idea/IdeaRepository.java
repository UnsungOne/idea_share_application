package com.idea.share.com.idea.share.idea;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends PagingAndSortingRepository <Idea, Long> {


    @Query(value = "UPDATE ideas SET score=score+1 WHERE id =:entryId", nativeQuery = true)
    void rateIdeaUp(@Param("entryId") Integer ideaId);


    @Query(value = "UPDATE ideas SET score=score-1 WHERE id =:entryId", nativeQuery = true)
    void rateIdeaDown(@Param("entryId") Integer ideaId);

}