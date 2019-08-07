package bd.edu.seu.coursemanagementserver.controller;

import bd.edu.seu.coursemanagementserver.model.Course;
import bd.edu.seu.coursemanagementserver.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses")
public class CourseController {
    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseRepository.findAll());
    }

    @PostMapping(value = "")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        Course savedCourse = courseRepository.save(course);
        return ResponseEntity.created(null).body(savedCourse);
    }
}

/*
db.course.insert({"code" : "CSE1021","title": "Discrete Math"})
 */