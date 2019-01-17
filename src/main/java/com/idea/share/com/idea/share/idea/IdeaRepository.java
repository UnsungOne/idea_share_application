package com.idea.share.com.idea.share.idea;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends PagingAndSortingRepository <Idea, Long> {


//    @Query("UPDATE ideas SET score=score+1 WHERE id =:entryId")
//    void rateIdeaUp(@Param("entryId") Integer ideaId);
//
//
//    @Query("UPDATE ideas SET score=score-1 WHERE id =:entryId")
//    void rateIdeaDown(@Param("entryId") Integer ideaId);

}