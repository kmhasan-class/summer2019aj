package bd.edu.seu.springbootdemo.controller;

import bd.edu.seu.springbootdemo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.springbootdemo.exception.ResourceDoesNotExistException;
import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import bd.edu.seu.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

// What we have done before
// GET http://172.17.3.87:8081/student/findAll
// GET http://172.17.3.87:8081/student/insert/{id}/{name}
// POST http://172.17.3.87:8081/student/insert/ -- student as body
// GET http://172.17.3.87:8081/student/findById/{id}
@RestController
//@RequestMapping(value = "/student")
@RequestMapping(value = "/api/v1/students")
public class StudentController {
//    @Autowired
//    private StudentRepository studentRepository;
//
//    private Map<Long, Student> studentMap;

//    public StudentController() {
//        studentMap = new HashMap<>();
//        // dummy data to follow
//        studentMap.put(1234l, new Student(1234l, "John Doe", LocalDate.of(2001, Month.APRIL, 12)));
//        studentMap.put(1235l, new Student(1235l, "Jane Doe", LocalDate.of(2002, Month.JUNE, 23)));
//    }

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET http://172.17.3.87:8081/api/v1/students/1234
    // DONE
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (ResourceDoesNotExistException e) {
            //e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
//        return studentRepository.findById(id).get();
    }

    // TODO try to find an answer for this after reading the article on naming guidelines
    @GetMapping("/findByName/{name}")
    public List<Student> getStudents(@PathVariable String name) {
        return null;
//        return studentRepository.findAllByNameContaining(name);
    }

    // GET http://172.17.3.87:8081/api/v1/students
    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> studentList = studentService.findAll();
        return ResponseEntity.ok(studentList);
    }
//
//    @GetMapping("/insert/{id}/{name}")
//    public Student insertStudent(@PathVariable long id, @PathVariable String name) {
//        Student student = new Student(id, name, LocalDate.now());
//        return studentRepository.save(student);

    // POST http://172.17.3.87:8081/api/v1/students
    @PostMapping("")
    // DONE
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
        try {
            Student insertedStudent = studentService.insertStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedStudent);
        } catch (ResourceAlreadyExistsException e) {
            //e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
//        return studentRepository.save(student);
    }

    // DELETE http://172.17.3.87:8081/api/v1/students
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable long id) {
        try {
            boolean deleted = studentService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoesNotExistException e) {
            return ResponseEntity.notFound().build();
//            e.printStackTrace();
        }
    }

}
