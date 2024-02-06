package kz.kbtu.algoapp.repository;

import kz.kbtu.algoapp.entity.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {
    @Query("{ 'subTopic._id': ?0 }")
    Optional<Content> findContentBySubTopicId(String id);
}
