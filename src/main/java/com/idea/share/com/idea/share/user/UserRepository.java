package com.idea.share.com.idea.share.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

     User findUserByEmail(String email);

     @Modifying
     @Query(value = "UPDATE users SET voted = true WHERE id = :entryId", nativeQuery = true)
     void changeVoteStatusToTrue(@Param("entryId") Integer ideaId);

     @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
     User userWithGivenEmail(@Param("email") String email);

}