package com.idea.share.com.idea.share.idea;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface IdeaRepository extends PagingAndSortingRepository <Idea, Integer> {

    @Modifying
    @Query(value = "UPDATE ideas SET score=score+1 WHERE id = :entryId", nativeQuery = true)
    int rateIdeaUp(@Param("entryId") Integer ideaId);

    @Modifying
    @Query(value = "UPDATE ideas SET score=score-1 WHERE id = :entryId", nativeQuery = true)
    int rateIdeaDown(@Param("entryId") Integer ideaId);

}