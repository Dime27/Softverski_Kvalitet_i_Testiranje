package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.exceptions.IndexNotValidException;
import mk.finki.ukim.wp.studentsapi.exceptions.InvalidParametersException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudyProgramNotExistingException;
import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudentRepository;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private JpaStudentRepository jpaStudentRepository;
    private JpaStudyProgramRepository jpaStudyProgramRepository;

    @Autowired
    public StudentServiceImpl(JpaStudentRepository jpaStudentRepository, JpaStudyProgramRepository jpaStudyProgramRepository){
        this.jpaStudentRepository = jpaStudentRepository;
        this.jpaStudyProgramRepository = jpaStudyProgramRepository;
    }


    @Override
    public List<Student> findAllStudents() {
        return jpaStudentRepository.findAll();
    }

    @Override
    public Student getStudentByIndex(String index) {
        return jpaStudentRepository.findByIndex(index);
    }

    @Override
    public List<Student> getStudentsByStydyProgramId(long id) {
        return jpaStudentRepository.findByStudyProgramId(id);
    }

    @Override
    public Student save(String index, String name, String lastName, String studyProgramName) throws IndexNotValidException, InvalidParametersException, StudyProgramNotExistingException {
        if (index.length() != 6 || !index.matches("[0-9]+")) {
            throw new IndexNotValidException(index);
        }

        if(index == null || name == null || lastName == null || studyProgramName == null){
            throw new InvalidParametersException();
        }

        if(jpaStudyProgramRepository.findByName(studyProgramName) == null){
            throw new StudyProgramNotExistingException(studyProgramName);
        }

        Student student = new Student();
        student.setIndex(index);
        student.setName(name);
        student.setLastName(lastName);

        StudyProgram studyProgram = jpaStudyProgramRepository.findByName(studyProgramName);
        student.setStudyProgram(studyProgram);

        return jpaStudentRepository.save(student);
    }

    @Override
    public Student update(String index, String name, String lastName, String studyProgramName) throws StudentNotFoundException, StudyProgramNotExistingException {
        Student student = jpaStudentRepository.findByIndex(index);
        if(student == null){
            throw new StudentNotFoundException(index);
        }

        if(jpaStudyProgramRepository.findByName(studyProgramName) == null){
            throw new StudyProgramNotExistingException(studyProgramName);
        }

        student.setIndex(index);
        student.setName(name);
        student.setLastName(lastName);

        StudyProgram studyProgram = jpaStudyProgramRepository.findByName(studyProgramName);
        student.setStudyProgram(studyProgram);

        return jpaStudentRepository.save(student);
    }

    @Override
    public Student deleteStudentByGivenIndex(String index) throws StudentNotFoundException {
        Student student = jpaStudentRepository.findByIndex(index);
        if(student == null){
            throw new StudentNotFoundException(index);
        }

        jpaStudentRepository.deleteByIndex(index);
        return student;
    }
}
