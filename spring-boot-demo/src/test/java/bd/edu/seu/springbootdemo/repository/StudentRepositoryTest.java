package bd.edu.seu.springbootdemo.repository;

import bd.edu.seu.springbootdemo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testCreateStudent() {
        Student student = new Student(1234l, "John Doe", LocalDate.now());
        Student savedStudent = studentRepository.save(student);
        assertEquals(student, savedStudent);
    }
}
