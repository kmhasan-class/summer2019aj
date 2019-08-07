package bd.edu.seu.coursemanagementserver.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionTest {
    @Test
    public void createSection() {
        Course course = new Course("CSE4047", "Advanced Java");

        Section section1 = new Section(course, "KMH", 1, 30, 52);
        Section section2 = new Section(course, "AR", 2, 25, 52);
/*
        course.addSection(section1);
        course.addSection(section2);
*/
        course.addSection(section1, section2);

        System.out.println(course);
        System.out.println(section1);
        System.out.println(section2);
    }
}
