package kz.kbtu.algoapp.repository;

import kz.kbtu.algoapp.entity.SubTopic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubTopicRepository extends MongoRepository<SubTopic, String> {
    @Query("{ '_id': ?0 }")
    Optional<SubTopic> findById(String id);
}
