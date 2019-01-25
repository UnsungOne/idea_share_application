package com.idea.share.com.idea.share.idea;

import com.idea.share.com.idea.share.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface IdeaRepository extends PagingAndSortingRepository <Idea, Integer> {

    @Modifying
    @Query(value = "UPDATE ideas SET score=score+1 WHERE id = :entryId", nativeQuery = true)
    int rateIdeaUp(@Param("entryId") Integer ideaId);

    @Modifying
    @Query(value = "UPDATE ideas SET score=score-1 WHERE id = :entryId", nativeQuery = true)
    int rateIdeaDown(@Param("entryId") Integer ideaId);

    @Query(value = "SELECT * FROM ideas WHERE active = true", nativeQuery = true)
    Page<Idea> fetchAll(PageRequest pageRequest);

    @Query(value = "SELECT * FROM ideas WHERE iduser = :entryId ", nativeQuery = true)
    Page<Idea> fetchUserIdeas(@Param("entryId") Integer ideaId, Pageable pageRequest);

    @Modifying
    @Query(value = "UPDATE ideas i SET i.title= :title, i.description = :description WHERE id = :entryID ", nativeQuery = true)
    void updateExistingIdea(@Param("title") String title, @Param("description") String description, @Param("entryID") Integer entryId);

}