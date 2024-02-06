package kz.kbtu.algoapp.repository;

import kz.kbtu.algoapp.entity.UserSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSubmissionRepository extends MongoRepository<UserSubmission, String> {
    @Query("{'user._id': ?0, 'quiz._id': ?1}")
    List<UserSubmission> findUserSubmissionByUserAndQuiz(String userId, String quizId);
}
