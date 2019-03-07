package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RunWith (SpringRunner.class)
@DataJpaTest
public class JpaStudyProgramRepositoryTest {

    @Autowired
    private JpaStudyProgramRepository jpaStudyProgramRepository;

    private StudyProgram studyProgram;
    private StudyProgram studyProgram2;

    @PostConstruct
    public void init(){
        studyProgram = new StudyProgram();
        studyProgram.setName("ASI");

        studyProgram2 = new StudyProgram();
        studyProgram2.setName("KNI");
    }

    /**
     *  Try to get a Study Program that doesn't exist
     */
    @Test
    public void findNotExistingStudyProgramByNameTest() {
        Assert.assertNull(jpaStudyProgramRepository.findByName("PET"));
    }

    /**
     *  Get a Study Program by name. Check if the return typ is NOT null
     */
    @Test
    public void findStudyProgramByNameNotNullTest() {
        StudyProgram savedStudyProgram = jpaStudyProgramRepository.save(studyProgram);
        Assert.assertNotNull(savedStudyProgram.getName());
    }

    /**
     *  Get a Study Program by name. Check if the return result is the expected one.
     */
    @Test
    public void findStudyProgramByNameTest() {
        StudyProgram savedStudyProgram = jpaStudyProgramRepository.save(studyProgram);
        StudyProgram findStudyProgram = jpaStudyProgramRepository.findByName("ASI");
        Assert.assertEquals(savedStudyProgram, findStudyProgram);
    }


    /**
     *  Get a Study Program by id. Check if such a Stydy Program exists
     */
    @Test
    public void findStudyProgramByIdIsPresentTest(){
        StudyProgram savedStudyProgram = jpaStudyProgramRepository.save(studyProgram);
        Optional<StudyProgram> findStudyProgram = jpaStudyProgramRepository.findById(savedStudyProgram.getId());
        Assert.assertTrue(findStudyProgram.isPresent());
    }

    /**
     *  Get a Study Program by id. Check if the return result is the expected one.
     */
    @Test
    public void findStudyProgramByIdTest() {
        StudyProgram savedStudyProgram = jpaStudyProgramRepository.save(studyProgram);
        Optional<StudyProgram> findStudyProgram = jpaStudyProgramRepository.findById(savedStudyProgram.getId());
        Assert.assertEquals(Optional.of(savedStudyProgram), findStudyProgram);
    }

    /**
     *  Delete a Study Program by id. Check if the number of left study programs matches the given one
     */
    @Test
    public void deleteStudyProgramByIdTest(){
        StudyProgram savedStudyProgram = jpaStudyProgramRepository.save(studyProgram);
        StudyProgram savedStudyProgram2 = jpaStudyProgramRepository.save(studyProgram2);

        jpaStudyProgramRepository.deleteById(savedStudyProgram.getId());

        List<StudyProgram> studyProgramList = jpaStudyProgramRepository.findAll();
        Assert.assertEquals(1, studyProgramList.size());
    }
}
