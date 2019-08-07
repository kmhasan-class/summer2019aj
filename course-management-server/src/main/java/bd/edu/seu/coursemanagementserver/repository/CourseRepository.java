package bd.edu.seu.coursemanagementserver.repository;

import bd.edu.seu.coursemanagementserver.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
}
