package bd.edu.seu.coursemanagementserver.controller;

import bd.edu.seu.coursemanagementserver.model.Course;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses")
public class CourseController {
    // TODO remove the fake course list and let's get something real from the cloud
    private List<Course> courseList;

    public CourseController() {
        courseList = new ArrayList<>();
        courseList.add(new Course("CSE4047", "Advanced Java"));
        courseList.add(new Course("CSE4048", "Advanced Java Lab"));
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseList);
    }

    @PostMapping(value = "")
    public ResponseEntity<Course> save(Course course) {
        courseList.add(course);
        return ResponseEntity.created(null).body(course);
    }
}

/*
db.course.insert({"code" : "CSE1021","title": "Discrete Math"})
 */