package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void testFindAll() {
        List<Student> studentList = studentService.findAll();
        assertNotNull(studentList);
        studentList.forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        Student expectedStudent = new Student(221l, "Sherlock Holmes", LocalDate.of(1981, Month.JULY, 31));
        Student actualStudent = studentService.findById(221l);
        assertEquals(expectedStudent, actualStudent);
    }

    // TODO add a test method for the save operation

    // TODO add a test method for the delete operation
}
