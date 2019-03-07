package mk.finki.ukim.wp.studentsapi.web;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class StudentRestControlerTest {

    @Mock
    private StudentService studentService;

    private StudentRestControler studentRestControler;

    private Student student1;
    private StudyProgram studyProgram;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        studentRestControler = new StudentRestControler(studentService);

        student1 = new Student();
        student1.setName("Petko");
        student1.setLastName("Petkovski");
        student1.setIndex("151010");
        studyProgram = new StudyProgram();
        studyProgram.setName("PET");
        studyProgram.setId(1L);
        student1.setStudyProgram(studyProgram);
    }

    /**
     *  Verify that getStudents() method from the StudentRestController invokes findAllStudents() method
     *  from StudentService exactly once
     */
    @Test
    public void getStudentsTest(){
        studentRestControler.getStudents();
        verify(studentService, times(1)).findAllStudents();
    }

    /**
     *  Verify that getStudent() method from the StudentRestController invokes getStudentByIndex() method
     *  from StudentService exactly once
     */
    @Test
    public void getStudentTest(){
        studentRestControler.getStudent(student1.getIndex());
        verify(studentService, times(1)).getStudentByIndex(student1.getIndex());
    }

    /**
     *  Verify that getStudentsByStudyProgram() method from the StudentRestController invokes getStudentsByStydyProgramId()
     *  method from StudentService exactly once
     */
    @Test
    public void getStudentsByStudyProgramTest(){
        studentRestControler.getStudentsByStudyProgram(studyProgram.getId());
        verify(studentService, times(1)).getStudentsByStydyProgramId(studyProgram.getId());
    }

    /**
     *  Verify that createStudent() method from the StudentRestController invokes save()
     *  method from StudentService exactly once
     */
    @Test
    public void createStudentTest(){
        studentRestControler.createStudent(student1);
        verify(studentService, times(1)).save(student1.getIndex(), student1.getName(), student1.getLastName(), student1.getStudyProgram().getName());
    }

    /**
     *  Verify that updateStudent() method from the StudentRestController invokes update()
     *  method from StudentService exactly once
     */
    @Test
    public void updateStudentTest(){
        studentRestControler.updateStudent(student1);
        verify(studentService, times(1)).update(student1.getIndex(), student1.getName(), student1.getLastName(), student1.getStudyProgram().getName());
    }

    /**
     *  Verify that deleteStudent() method from the StudentRestController invokes deleteStudentByGivenIndex()
     *  method from StudentService exactly once
     */
    @Test
    public void deleteStudentTest(){
        studentRestControler.deleteStudent(student1);
        verify(studentService, times(1)).deleteStudentByGivenIndex(student1.getIndex());
    }
}
