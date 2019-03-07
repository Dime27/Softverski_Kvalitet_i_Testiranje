package mk.finki.ukim.wp.studentsapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student")
public class Student {

    @Id
    @Column(name = "index_ID")
    private String index;

    private String name;

    @Column(name="last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    StudyProgram studyProgram;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }
}
