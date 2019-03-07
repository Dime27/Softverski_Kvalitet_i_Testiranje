package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Optional;

public interface JpaStudyProgramRepository extends JpaRepository<StudyProgram, Long>{

    Optional<StudyProgram> findById(Long id);

    StudyProgram findByName(String name);

    @Modifying
    @Transactional
    void deleteById(Long id);

    /*@Modifying
    @Query("delete from Token t where t.id = ?1")
    void delete(Long entityId);*/

    /*@Modifying
    @Query("delete from StudyProgram sp where sp.id=:id")
    StudyProgram deleteById(@Param("id")Long id);*/
}
