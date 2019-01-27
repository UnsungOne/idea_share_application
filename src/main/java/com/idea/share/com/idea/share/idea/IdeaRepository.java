package com.idea.share.com.idea.share.idea;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    void rateIdeaUp(@Param("entryId") Integer ideaId);

    @Modifying
    @Query(value = "UPDATE ideas SET score=score-1 WHERE id = :entryId", nativeQuery = true)
    void rateIdeaDown(@Param("entryId") Integer ideaId);

    @Query(value = "SELECT * FROM ideas WHERE active = true", nativeQuery = true)
    Page<Idea> fetchAll(PageRequest pageRequest);

    @Query(value = "SELECT * FROM ideas WHERE iduser = :entryId ", nativeQuery = true)
    Page<Idea> fetchUserIdeas(@Param("entryId") Integer ideaId, Pageable pageRequest);

    @Modifying
    @Query("UPDATE Idea i SET i.title= ?1, i.description = ?2 WHERE id = ?3")
    void updateExistingIdea(String title, String description, Integer entryId);

}