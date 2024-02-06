package kz.kbtu.algoapp.repository;

import kz.kbtu.algoapp.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    @Query("{ 'quiz._id': ?0 }")
    Optional<Topic> findTopicByQuizID(String id);
}
