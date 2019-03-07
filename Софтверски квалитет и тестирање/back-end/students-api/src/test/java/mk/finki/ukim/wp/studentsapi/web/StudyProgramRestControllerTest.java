package mk.finki.ukim.wp.studentsapi.web;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class StudyProgramRestControllerTest {

    @Mock
    private StudyProgramService studyProgramService;

    private StudyProgramRestControler studyProgramRestControler;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        studyProgramRestControler = new StudyProgramRestControler(studyProgramService);
    }

    /**
     *  Verify that getStudyPrograms() method from StudyProgramRestController invokes
     *  findAll() method from StudyProgramService exactly once
     * */
    @Test
    public void getStudyProgramsVerifyTest(){
        studyProgramRestControler.getStudyPrograms();
        Mockito.verify(studyProgramService, Mockito.times(1)).findAll();
    }

    /**
     *  Verify that createStudyProgram() method from StudyProgramRestController invokes
     *  save() method from StudyProgramService exactly once
     * */
    @Test
    public void createStudyProgramVerifyTest(){
        StudyProgram studyProgramKNI = new StudyProgram();
        studyProgramKNI.setId(1L);
        studyProgramKNI.setName("KNI");

        studyProgramRestControler.createStudyProgram(studyProgramKNI);
        Mockito.verify(studyProgramService, Mockito.times(1)).save(studyProgramKNI.getName());
    }

    /**
     *  Verify that updateStudent() method from StudyProgramRestController invokes
     *  update() method from StudyProgramService exactly once
     * */
    @Test
    public void updateStudentVerifyTest() {
        StudyProgram studyProgramKNI = new StudyProgram();
        studyProgramKNI.setId(1L);
        studyProgramKNI.setName("KNI");

        studyProgramRestControler.updateStudent(studyProgramKNI);
        Mockito.verify(studyProgramService, Mockito.times(1)).update(studyProgramKNI.getId(), studyProgramKNI.getName());
    }

    /**
     *  Verify that deleteStudyPrograms() method from StudyProgramRestController invokes
     *  deleteById() method from StudyProgramService exactly once
     * */
    @Test
    public void deleteStudyProgramVerifyTest(){
        studyProgramRestControler.deleteStudyProgram(1L);
        Mockito.verify(studyProgramService, Mockito.times(1)).deleteById(1L);
    }
}