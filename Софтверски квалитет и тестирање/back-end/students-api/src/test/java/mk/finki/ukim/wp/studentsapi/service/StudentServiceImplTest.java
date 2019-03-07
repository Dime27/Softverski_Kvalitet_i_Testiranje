package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.exceptions.IndexNotValidException;
import mk.finki.ukim.wp.studentsapi.exceptions.InvalidParametersException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudyProgramNotExistingException;
import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudentRepository;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Mock
    private JpaStudentRepository jpaStudentRepository;

    @Mock
    private JpaStudyProgramRepository jpaStudyProgramRepository;

    private StudentServiceImpl studentService;


    private ArrayList<Student> studentsList;
    private StudyProgram studyProgram;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(jpaStudentRepository, jpaStudyProgramRepository);

        Student student1 = new Student();
        student1.setName("Dimitar");
        student1.setLastName("Dimitrovski");
        student1.setIndex("151010");
        studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        studyProgram.setId(1L);
        student1.setStudyProgram(studyProgram);

        Student student2 = new Student();
        student2.setName("Petko");
        student2.setLastName("Petkovski");
        student2.setIndex("151111");
        student2.setStudyProgram(studyProgram);

        studentsList = new ArrayList<>();
        studentsList.add(student1);
        studentsList.add(student2);
    }

    /**
     *  Verify that the findAll() method in jpaStudentRepository is called exactly once
     */
    @Test
    public void findAllStudentsTest1() {
        studentService.findAllStudents();
        verify(jpaStudentRepository, times(1)).findAll();
    }

    /**
     *  Check if the service method getStudentByIndex() returns the expected student
     */
    @Test
    public void getStudentByIndexTest1() {
        when(jpaStudentRepository.findByIndex("151010")).thenReturn(studentsList.get(1));
        Assert.assertEquals(studentsList.get(1), studentService.getStudentByIndex("151010"));
    }

    /**
     * verifying that getStudentByIndex was called exactly once
     */
    @Test
    public void getStudentByIndexTest2() {
        studentService.getStudentByIndex("151010");
        verify(jpaStudentRepository, times(1)).findByIndex("151010");
    }

    /**
     * Check if the getStudentsByStydyProgramId() method returns the amount of elements expected
     */
    @Test
    public void getStudentsByStudyProgramIdTest1(){
        Student student = new Student();
        student.setIndex("111111");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);
        studentsList.add(student);

        when(jpaStudentRepository.findByStudyProgramId(studyProgram.getId())).thenReturn(studentsList);
        Assert.assertEquals(3, studentService.getStudentsByStydyProgramId(studyProgram.getId()).size());
    }

    /**
     * verifying that getStudentsByStydyProgramId was called
     */
    @Test
    public void getStudentsByStydyProgramIdTest2() {
        Student student = new Student();
        student.setIndex("111111");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);
        studentsList.add(student);

        studentService.getStudentsByStydyProgramId(studyProgram.getId());
        verify(jpaStudentRepository, times(1)).findByStudyProgramId(studyProgram.getId());
    }

    /**
     * index.length = 0
     */
    @Test(expected = IndexNotValidException.class)
    public void saveTest1() throws IndexNotValidException {
        Student student = new Student();
        student.setIndex("");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  index with letters
     */
    @Test(expected = IndexNotValidException.class)
    public void saveTest2() throws IndexNotValidException {
        Student student = new Student();
        student.setIndex("aaabbb");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  index with 5 numbers
     */
    @Test(expected = IndexNotValidException.class)
    public void saveTest3() throws IndexNotValidException {
        Student student = new Student();
        student.setIndex("12345");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  index = null
     */
    @Test(expected = NullPointerException.class)
    public void saveTest4() throws NullPointerException {
        Student student = new Student();
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  name = null
     */
    @Test(expected = InvalidParametersException.class)
    public void saveTest5() throws InvalidParametersException {
        Student student = new Student();
        student.setIndex("123456");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  lastName = null
     */
    @Test(expected = InvalidParametersException.class)
    public void saveTest6() throws InvalidParametersException {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Gjorgji");
        student.setStudyProgram(studyProgram);

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     *  studyProgram = null
     */
    @Test(expected = NullPointerException.class)
    public void saveTest7() throws NullPointerException {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");

        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     * all parameters are good
     */
    @Test
    public void saveTest8() {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setStudyProgram(studyProgram);

        when(jpaStudyProgramRepository.findByName(studyProgram.getName())).thenReturn(studyProgram);
        when(jpaStudentRepository.save(anyObject())).thenReturn(student);
        Student std = studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
        Assert.assertEquals(student.getIndex(), std.getIndex());
    }

    /**
     * verifying that save was called
     */
    @Test
    public void saveTest9() {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Bojan");
        student.setLastName("Bojanovski");
        student.setStudyProgram(studyProgram);

        when(jpaStudyProgramRepository.findByName(studyProgram.getName())).thenReturn(studyProgram);
        when(jpaStudentRepository.save(anyObject())).thenReturn(student);
        studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
        verify(jpaStudentRepository, times(1)).save(any());
    }

    /**
     *  if student does not exist
     */
    @Test(expected = StudentNotFoundException.class)
    public void updateTest1() throws StudentNotFoundException {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setStudyProgram(studyProgram);

        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(null);
        studentService.update(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     * if study program does not exist
     */
    @Test(expected = StudyProgramNotExistingException.class)
    public void updateTest2() throws StudyProgramNotExistingException {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setStudyProgram(studyProgram);

        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(student);
        when(jpaStudyProgramRepository.findByName(studyProgram.getName())).thenReturn(null);
        studentService.update(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    /**
     * all parameters are good
     * changing the lastName
     */
    @Test
    public void updateTest3() {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setStudyProgram(studyProgram);

        Student student2 = new Student();
        student2.setIndex("123456");
        student2.setName("Petko");
        // smeneto prezime
        student2.setLastName("Petrovski");
        student2.setStudyProgram(studyProgram);

        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(student);
        when(jpaStudyProgramRepository.findByName(studyProgram.getName())).thenReturn(studyProgram);
        when(jpaStudentRepository.save(anyObject())).thenReturn(student2);
        Student std = studentService.update(student2.getIndex(), student2.getName(), student2.getLastName(), student2.getStudyProgram().getName());
        Assert.assertEquals(student2.getLastName(), std.getLastName());
    }

    /**
     * verifying that update was called
     */
    @Test
    public void updateTest4() {
        Student student = new Student();
        student.setIndex("123456");
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setStudyProgram(studyProgram);

        Student student2 = new Student();
        student2.setIndex("123456");
        student2.setName("Petko");
        // smeneto prezime
        student2.setLastName("Petrovski");
        student2.setStudyProgram(studyProgram);

        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(student);
        when(jpaStudyProgramRepository.findByName(studyProgram.getName())).thenReturn(studyProgram);
        when(jpaStudentRepository.save(anyObject())).thenReturn(student2);
        studentService.update(student2.getIndex(), student2.getName(), student2.getLastName(), student2.getStudyProgram().getName());
        verify(jpaStudentRepository, times(1)).save(any());
    }


    /**
     *  Check if deleteStudentByGivenIndex() in StudentService invokes deleteByIndex() in StudentRepository
     *  exactly once
     */
    @Test
    public void deleteStudentByGivenIndexTest1() {
        Student student = studentsList.get(0);
        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(student);
        studentService.deleteStudentByGivenIndex(student.getIndex());
        verify(jpaStudentRepository, times(1)).deleteByIndex(student.getIndex());
    }

    /**
     *  Check if trying to delete non existing student throws StudentNotFoundException
     */
    @Test(expected = StudentNotFoundException.class)
    public void deleteStudentByGivenIndexTest2() throws StudentNotFoundException {
        Student student = new Student();
        student.setIndex("111111");
        student.setName("Gjorgji");
        student.setLastName("Gjorgjievski");
        student.setStudyProgram(studyProgram);

        when(jpaStudentRepository.findByIndex(student.getIndex())).thenReturn(null);
        studentService.deleteStudentByGivenIndex(student.getIndex());
    }
}