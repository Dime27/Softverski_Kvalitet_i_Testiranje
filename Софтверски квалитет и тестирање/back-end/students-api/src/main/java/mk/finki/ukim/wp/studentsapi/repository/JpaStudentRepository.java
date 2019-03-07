package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface JpaStudentRepository extends JpaRepository<Student, String> {

    Student findByIndex(String index);

    List<Student> findByStudyProgramId(long id);

    @Modifying
    @Transactional
    void deleteByIndex(String index);
}
