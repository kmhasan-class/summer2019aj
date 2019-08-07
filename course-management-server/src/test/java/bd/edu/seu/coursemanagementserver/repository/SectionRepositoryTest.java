package bd.edu.seu.coursemanagementserver.repository;

import bd.edu.seu.coursemanagementserver.model.Course;
import bd.edu.seu.coursemanagementserver.model.Section;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SectionRepositoryTest {
    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testFindAll() {
        List<Section> sectionList = sectionRepository.findAll();
        sectionList.forEach(System.out::println);
    }

    @Test
    public void testSave() {
        Course course = new Course("CSE4047", "Advanced Java");

        Section section1 = new Section(course, "KMH", 1, 30, 52);
        Section section2 = new Section(course, "AR", 2, 25, 52);

        course.addSection(section1);
        course.addSection(section2);

        courseRepository.save(course);
        sectionRepository.save(section1);
        sectionRepository.save(section2);
    }
}
