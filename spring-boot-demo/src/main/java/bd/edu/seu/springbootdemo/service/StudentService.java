package bd.edu.seu.springbootdemo.service;

import bd.edu.seu.springbootdemo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.springbootdemo.exception.ResourceDoesNotExistException;
import bd.edu.seu.springbootdemo.model.Student;
import bd.edu.seu.springbootdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student findById(long id) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else throw new ResourceDoesNotExistException(id + "");
    }

    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
    }

    public boolean deleteById(long id) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.ifPresent(student -> studentRepository.deleteById(id));
        optionalStudent.orElseThrow(() -> new ResourceDoesNotExistException(id + ""));
        return true;
    }

    public Student insertStudent(Student student) throws ResourceAlreadyExistsException {
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        if (optionalStudent.isPresent()) {
            throw new ResourceAlreadyExistsException(student.getId() + "");
        } else {
            return studentRepository.save(student);
        }
    }

    public Student updateStudent(long id, Student student) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            // The following line ensures that the ID doesn't change
            student.setId(id);
            return studentRepository.save(student);
        } else {
            throw new ResourceDoesNotExistException(id + "");
        }
    }
}
