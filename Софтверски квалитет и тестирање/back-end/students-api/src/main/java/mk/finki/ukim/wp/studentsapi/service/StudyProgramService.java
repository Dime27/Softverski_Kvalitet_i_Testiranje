package mk.finki.ukim.wp.studentsapi.service;

import mk.finki.ukim.wp.studentsapi.model.StudyProgram;

import java.util.List;

public interface StudyProgramService {

    List<StudyProgram> findAll();

    StudyProgram save(String name);

    StudyProgram update(Long id, String name);

    void deleteById(long id);
}

