package bd.edu.seu.springbootdemo.service;

import bd.edu.seu.springbootdemo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Test
    public void testInsertNewStudent() {
        Student student = new Student(1234, "John Doe", LocalDate.of(2011, Month.JANUARY, 1));
        try {
            Student insertedStudent = studentService.insertStudent(student);
            assertEquals(student, insertedStudent);
        } catch (ResourceAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void testInsertExistingStudent() throws ResourceAlreadyExistsException {
        Student student1 = new Student(1234, "John Doe", LocalDate.of(2011, Month.JANUARY, 1));
        Student student2 = new Student(1234, "Jane Doe", LocalDate.of(2012, Month.MAY, 1));
        try {
            Student insertedStudent = studentService.insertStudent(student1);
            assertEquals(student1, insertedStudent);
        } catch (ResourceAlreadyExistsException e) {
            e.printStackTrace();
        }

//        try {
            Student insertedStudent = studentService.insertStudent(student2);
            assertEquals(student2, insertedStudent);
//        } catch (ResourceAlreadyExistsException e) {
//            throw e;
//        }
    }
}
