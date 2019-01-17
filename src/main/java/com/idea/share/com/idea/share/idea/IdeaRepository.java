package com.idea.share.com.idea.share.idea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {

//    @Query("UPDATE ideas SET score=score+1 WHERE id =:entryId")
//    void rateIdeaUp(@Param("entryId") Integer id);
//
//
//    @Query("UPDATE ideas SET score=score-1 WHERE id =:entryId")
//    void rateIdeaDown(@Param("entryId") Integer id);

}