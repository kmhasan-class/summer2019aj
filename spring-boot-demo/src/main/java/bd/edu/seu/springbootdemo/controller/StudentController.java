package bd.edu.seu.springbootdemo.controller;

import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    private Map<Long, Student> studentMap;

    public StudentController() {
        studentMap = new HashMap<>();
        // dummy data to follow
        studentMap.put(1234l, new Student(1234l, "John Doe", LocalDate.of(2001, Month.APRIL, 12)));
        studentMap.put(1235l, new Student(1235l, "Jane Doe", LocalDate.of(2002, Month.JUNE, 23)));
    }

    @GetMapping("/findById/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentRepository.findById(id).get();
    }

    @GetMapping("/findByName/{name}")
    public List<Student> getStudents(@PathVariable String name) {
        return studentRepository.findAllByNameContaining(name);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Student>> getStudents() {
//        List<Student> studentList = new ArrayList<>();
//        studentRepository.findAll().forEach(studentList::add);
//        return studentList;
        List<Student> studentList = null;
        try {
            studentList = studentMap.values().stream().collect(Collectors.toList());
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
//    }

    @PostMapping("/insert")
    public Student insertStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

}
