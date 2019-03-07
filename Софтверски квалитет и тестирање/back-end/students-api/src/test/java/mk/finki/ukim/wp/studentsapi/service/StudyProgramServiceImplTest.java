package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.impl.StudyProgramServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class StudyProgramServiceImplTest {

    @Mock
    private JpaStudyProgramRepository jpaStudyProgramRepository;

    private StudyProgramServiceImpl studyProgramService;

    private List<StudyProgram> studyProgramList;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        studyProgramService = new StudyProgramServiceImpl(jpaStudyProgramRepository);

        StudyProgram studyProgramKNI = new StudyProgram();
        StudyProgram studyProgramPET = new StudyProgram();
        studyProgramList = new ArrayList<>();

        studyProgramKNI.setName("KNI");
        studyProgramKNI.setId(1L);
        studyProgramPET.setName("PET");
        studyProgramPET.setId(2L);

        studyProgramList.add(studyProgramKNI);
        studyProgramList.add(studyProgramPET);
    }

    /**
     *  Verify that the findAll() method in StudyProgramService invokes the findAll() method in StudyProgramRepository
     *  exactly once
     */
    @Test
    public void findAllStudyProgramsVerifyTest(){
        studyProgramService.findAll();
        Mockito.verify(jpaStudyProgramRepository, Mockito.times(1)).findAll();
    }

    /**
     *  Check if the findAll() method in StudyProgramService returns value other than null
     * */
    @Test
    public void findAllStudyProgramsTest1(){
        Mockito.when(jpaStudyProgramRepository.findAll()).thenReturn(studyProgramList);
        Assert.assertNotNull(studyProgramService.findAll());
    }

    /**
     *   Check if the findAll() method in StudyProgramService returns the expected amount of values
     * */
    @Test
    public void findAllStudyProgramsTest2(){
        Mockito.when(jpaStudyProgramRepository.findAll()).thenReturn(studyProgramList);
        Assert.assertEquals(2, studyProgramService.findAll().size());
    }

    /**
     *  Verify that the save() method in StudyProgramService invokes the save() method in StudyProgramRepository
     *  exactly once
    * */
    @Test
    public void saveStudyProgramVerifyTest1(){
        studyProgramService.save(any());
        Mockito.verify(jpaStudyProgramRepository, Mockito.times(1)).save(any());
    }

    /**
     *  Check if the save() method in StudyProgramService returns value other than null
     * */
    @Test
    public void saveStudyProgramTest1(){
        StudyProgram studyProgramIKI = new StudyProgram();
        studyProgramIKI.setName("IKI");
        studyProgramIKI.setId(5L);
        Mockito.when(jpaStudyProgramRepository.save(any())).thenReturn(studyProgramIKI);
        Assert.assertNotNull(studyProgramService.save(studyProgramIKI.getName()));
    }

    /**
     *  Check if the save() method in StudyProgramService returns the expected value
     * */
    @Test
    public void saveStudyProgramTest2(){
        StudyProgram studyProgramIKI = new StudyProgram();
        studyProgramIKI.setName("IKI");
        studyProgramIKI.setId(5L);
        Mockito.when(jpaStudyProgramRepository.save(any())).thenReturn(studyProgramIKI);
        Assert.assertEquals("IKI", studyProgramService.save(studyProgramIKI.getName()).getName());
    }

    /**
     *  Verify that the update() method in StudyProgramService invokes the findById() method in StudyProgramRepository
     *  exactly once
     * */
    @Test
    public void updateStudyProgramVerifyTest1(){
        studyProgramService.update(1L, "KNI");
        Mockito.verify(jpaStudyProgramRepository, Mockito.times(1)).findById(1L);
    }

    /**
     *  Verify that the update() method in StudyProgramService invokes the save() method in StudyProgramRepository
     *  exactly once
     * */
    @Test
    public void updateStudyProgramVerifyTest2(){
        studyProgramService.update(1L, "KNI");
        Mockito.verify(jpaStudyProgramRepository, Mockito.times(1)).save(any());
    }

    /**
     *  Check if the update() method in StudyProgramService returns value other than null
     * */
    @Test
    public void updateStudyProgramTest1(){
        long studyProgramId = studyProgramList.get(0).getId();
        Mockito.when(jpaStudyProgramRepository.findById(studyProgramId)).thenReturn(Optional.of(studyProgramList.get(0)));
        Mockito.when(jpaStudyProgramRepository.save(any())).thenReturn(studyProgramList.get(0));
        Assert.assertNotNull(studyProgramService.update(studyProgramId, "ASI"));
    }

    /**
     *  Check if the update() method in StudyProgramService returns the expected value
     * */
    @Test
    public void updateStudyProgramTest2(){
        long studyProgramId = studyProgramList.get(0).getId();
        Mockito.when(jpaStudyProgramRepository.findById(studyProgramId)).thenReturn(Optional.of(studyProgramList.get(0)));
        Mockito.when(jpaStudyProgramRepository.save(any())).thenReturn(studyProgramList.get(0));
        Assert.assertEquals(studyProgramList.get(0), studyProgramService.update(studyProgramId, "ASI"));
    }

    /**
     *  Verify that the deleteById() method in StudyProgramService invokes the deleteById() method in StudyProgramRepository
     *  exactly once
     * */
    @Test
    public void deleteStudyProgramByIdVerifyTest(){
        studyProgramService.deleteById(1L);
        Mockito.verify(jpaStudyProgramRepository, Mockito.times(1)).deleteById(1L);
    }
}
