package mk.finki.ukim.wp.studentsapi.web;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping(value = "/study_programs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudyProgramRestControler {
    StudyProgramService studyProgramService;

    @Autowired
    public StudyProgramRestControler(StudyProgramService studyProgramService){
        this.studyProgramService = studyProgramService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<StudyProgram> getStudyPrograms() {
        return studyProgramService.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public StudyProgram createStudyProgram(@RequestBody StudyProgram studyProgram) {
        return studyProgramService.save(studyProgram.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public StudyProgram updateStudent(@RequestBody StudyProgram studyProgram/*@PathVariable Long id, @RequestParam String name*/){
        return studyProgramService.update(studyProgram.getId(), studyProgram.getName()/*id,name*/);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudyProgram(@PathVariable Long id) {
        studyProgramService.deleteById(id);
    }
}
