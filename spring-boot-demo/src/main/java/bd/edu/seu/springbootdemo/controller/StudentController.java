package bd.edu.seu.springbootdemo.controller;

import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import bd.edu.seu.springbootdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable long id) {
        return null;
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
//        List<Student> studentList = new ArrayList<>();
//        studentRepository.findAll().forEach(studentList::add);
//        return studentList;
        List<Student> studentList = null;
        try {
//            studentList = studentMap.values().stream().collect(Collectors.toList());
            return ResponseEntity.ok(studentList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(studentList);
        }
    }
//
//    @GetMapping("/insert/{id}/{name}")
//    public Student insertStudent(@PathVariable long id, @PathVariable String name) {
//        Student student = new Student(id, name, LocalDate.now());
//        return studentRepository.save(student);

    // POST http://172.17.3.87:8081/api/v1/students
    @PostMapping("")
    public Student insertStudent(@RequestBody Student student) {
        return null;
//        return studentRepository.save(student);
    }

    // DELETE http://172.17.3.87:8081/api/v1/students
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteStudent(@PathVariable long id) {
//        boolean deleted = studentService.deleteById(id);
//        if (deleted)
//            return ResponseEntity.ok(id);
//        else return ResponseEntity.notFound().build();
        return null;
    }

}
