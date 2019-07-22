package bd.edu.seu.springbootdemo.repository;

import bd.edu.seu.springbootdemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findAllByName(String name);
    List<Student> findAllByNameContaining(String partialName);
    List<Student> findAllByDobAfter(LocalDate localDate);
}
