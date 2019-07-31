package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService implements GenericService<Student, Long> {
    // TODO get rid of the repository

    private StudentRepository studentRepository;
    @Value("${baseUrl}/students")
    private String baseUrl;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        System.out.printf("Base URL: [%s]\n", baseUrl);
    }

    public Student findById(Long id) {
        System.out.printf("findById Base URL: [%s]\n", baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Student> response = restTemplate.exchange(
                baseUrl + "/" + id,
                HttpMethod.GET,
                null,
                Student.class);
        Student student = response.getBody();
        System.out.println(student);
        return student;
    }

    public List<Student> findAll() {
        System.out.printf("findAll Base URL: [%s]\n", baseUrl);
        //return studentRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Student>>(){});
        List<Student> studentList = response.getBody();
        return studentList;
    }

    // TODO
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    // TODO
    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
