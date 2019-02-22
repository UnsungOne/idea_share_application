package com.idea.share.com.idea.share.idea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVotedIdeasRepository extends CrudRepository<UserVotedIdeas, Integer> {
}
