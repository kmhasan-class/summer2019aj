package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.repository.StudentRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService implements GenericService<Student, Long> {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public List<Student> findAll() {
        //return studentRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:8082/student/findAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>(){});
        List<Student> studentList = response.getBody();
        return studentList;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
