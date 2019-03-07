package mk.finki.ukim.wp.studentsapi.web;

import mk.finki.ukim.wp.studentsapi.exceptions.IndexNotValidException;
import mk.finki.ukim.wp.studentsapi.exceptions.InvalidParametersException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudentNotFoundException;
import mk.finki.ukim.wp.studentsapi.exceptions.StudyProgramNotExistingException;
import mk.finki.ukim.wp.studentsapi.model.Student;
import mk.finki.ukim.wp.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentRestControler {
    @Autowired
    private StudentService studentService;
    public StudentRestControler(StudentService studentService){
        this.studentService = studentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable String index) {
        return studentService.getStudentByIndex(index);
    }

    @RequestMapping(value = "/by_study_program/{id}", method = RequestMethod.GET)
    public List<Student> getStudentsByStudyProgram(@PathVariable Long id) {
        return studentService.getStudentsByStydyProgramId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ExceptionHandler({InvalidParametersException.class, IndexNotValidException.class, StudyProgramNotExistingException.class})
    public Student createStudent(@RequestBody Student student) {
        return studentService.save(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.PATCH)
    @ExceptionHandler({StudyProgramNotExistingException.class, IndexNotValidException.class, StudentNotFoundException.class})
    public Student updateStudent(@RequestBody Student student){
        return studentService.update(student.getIndex(), student.getName(), student.getLastName(), student.getStudyProgram().getName());
    }

    @RequestMapping(value = "/{index}", method = RequestMethod.DELETE)
    @ExceptionHandler(StudentNotFoundException.class)
    public void deleteStudent(@RequestBody Student student) {
        studentService.deleteStudentByGivenIndex(student.getIndex());
    }
}
