package bd.edu.seu.springbootdemo.controller;

import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    public StudentController() {
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
    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
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
