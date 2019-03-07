package mk.finki.ukim.wp.studentsapi.repository;

import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaStudentRepositoryTest {

    @Autowired
    JpaStudentRepository jpaStudentRepository;

    @Autowired
    JpaStudyProgramRepository jpaStudyProgramRepository;

    /**
     * No student with index 123456
     * findByIndex returns null value
     */
    @Test
    public void findByIndexTest1(){
        Student student = jpaStudentRepository.findByIndex("123456");
        Assert.assertNull(student);
    }

    /**
     *  Returns a student with given index
     *  findByIndex returns NotNull value
     */
    @Test
    public void findByIndexTest2(){
        Student student = new Student();
        student.setName("Petko");
        student.setLastName("Petkovski");
        student.setIndex("112233");

        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        jpaStudyProgramRepository.save(studyProgram);
        student.setStudyProgram(studyProgram);
        jpaStudentRepository.save(student);

        Student savedStudent = jpaStudentRepository.findByIndex(student.getIndex());
        Assert.assertNotNull(savedStudent);
    }

    /**
     *  Returns a student with given index
     *  findByIndex returns NotNull value
     */
    @Test
    public void findByIndexTest3(){
        Student student = new Student();
        student.setName("Dimitar");
        student.setLastName("Dimitrovski");
        student.setIndex("151010");

        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        jpaStudyProgramRepository.save(studyProgram);
        student.setStudyProgram(studyProgram);

        Student savedStudent =  jpaStudentRepository.save(student);
        Student getStudentFromDatabase = jpaStudentRepository.findByIndex(student.getIndex());
        Assert.assertEquals(savedStudent, getStudentFromDatabase);
    }

    /**
     *  Returns an empty list but not null
     */
    @Test
    public void findByStudyProgramIdTest4(){
        List<Student> studentList = jpaStudentRepository.findByStudyProgramId(234);
        Assert.assertNotNull(studentList);
    }

    /**
     *  Returns a list with 1 student for a given study program
     */
    @Test
    public void findByStudyProgramIdTest5() {
        Student student = new Student();
        student.setName("Dimitar");
        student.setLastName("Dimitrovski");
        student.setIndex("151010");

        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        jpaStudyProgramRepository.save(studyProgram);
        student.setStudyProgram(studyProgram);
        jpaStudentRepository.save(student);

        List<Student> studentList = jpaStudentRepository.findByStudyProgramId(studyProgram.getId());
        Assert.assertEquals(1, studentList.size());
    }

    /**
     *  Delete an existing student with given index
     */
    @Test
    public void deleteByIndexTest6() {
        Student student = new Student();
        student.setName("Dimitar");
        student.setLastName("Dimitrovski");
        student.setIndex("151010");

        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        jpaStudyProgramRepository.save(studyProgram);
        student.setStudyProgram(studyProgram);
        jpaStudentRepository.save(student);

        Student student1 = new Student();
        student1.setName("Petko");
        student1.setLastName("Petkovski");
        student1.setIndex("151111");
        student1.setStudyProgram(studyProgram);
        jpaStudentRepository.save(student1);

        List<Student> students = jpaStudentRepository.findAll();
        Assert.assertEquals(2, students.size());

        jpaStudentRepository.deleteByIndex(student.getIndex());
        students = jpaStudentRepository.findAll();
        Assert.assertEquals(1, students.size());
    }
}
