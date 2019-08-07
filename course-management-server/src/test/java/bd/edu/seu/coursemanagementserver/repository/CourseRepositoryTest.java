package bd.edu.seu.coursemanagementserver.repository;

import bd.edu.seu.coursemanagementserver.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testFindAll() {
        List<Course> courseList = courseRepository.findAll();
        courseList.forEach(System.out::println);
    }
}
