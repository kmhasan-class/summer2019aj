package bd.edu.seu.coursemanagementserver.repository;

import bd.edu.seu.coursemanagementserver.model.Course;
import bd.edu.seu.coursemanagementserver.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends MongoRepository<Section, String> {
}
