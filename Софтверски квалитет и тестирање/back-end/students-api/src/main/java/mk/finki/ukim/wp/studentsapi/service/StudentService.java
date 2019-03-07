package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.exceptions.IndexNotValidException;
import mk.finki.ukim.wp.studentsapi.exceptions.InvalidParametersException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudyProgramNotExistingException;
import mk.finki.ukim.wp.studentsapi.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAllStudents();

    Student getStudentByIndex(String index);

    List<Student> getStudentsByStydyProgramId(long id);

    Student save(String index, String name, String lastName, String studyProgramName) throws IndexNotValidException, InvalidParametersException, StudyProgramNotExistingException;

    Student update(String index, String name, String lastName, String studyProgramName) throws IndexNotValidException, StudentNotFoundException, StudyProgramNotExistingException;

    Student deleteStudentByGivenIndex(String index) throws StudentNotFoundException;
}