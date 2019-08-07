package bd.edu.seu.coursemanagementserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
    @Test
    public void createCourse() {
        Course course = new Course("CSE4047", "Advanced Java");
        System.out.println(course);
    }
}
