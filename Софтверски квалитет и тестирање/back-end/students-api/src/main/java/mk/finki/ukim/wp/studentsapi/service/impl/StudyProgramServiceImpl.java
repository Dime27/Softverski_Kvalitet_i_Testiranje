package mk.finki.ukim.wp.studentsapi.service.impl;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;
import mk.finki.ukim.wp.studentsapi.repository.JpaStudyProgramRepository;
import mk.finki.ukim.wp.studentsapi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramServiceImpl implements StudyProgramService {

    private JpaStudyProgramRepository jpaStudyProgramRepository;

    @Autowired
    public StudyProgramServiceImpl(JpaStudyProgramRepository jpaStudyProgramRepository){
        this.jpaStudyProgramRepository = jpaStudyProgramRepository;
    }

    @Override
    public List<StudyProgram> findAll() {
        return jpaStudyProgramRepository.findAll();
    }

    @Override
    public StudyProgram save(String name) {
        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName(name);
        return jpaStudyProgramRepository.save(studyProgram);
    }

    @Override
    public StudyProgram update(Long id, String name) {
        Optional<StudyProgram> studyProgram = jpaStudyProgramRepository.findById(id);
        StudyProgram updatedStudyProgram = new StudyProgram();
        if(studyProgram.isPresent()){
            updatedStudyProgram.setId(id);
            updatedStudyProgram.setName(name);
        }
        return jpaStudyProgramRepository.save(updatedStudyProgram);
    }

    @Override
    public void deleteById(long id) {
        jpaStudyProgramRepository.deleteById(id);
    }
}
