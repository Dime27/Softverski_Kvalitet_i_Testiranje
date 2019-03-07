import React, { Component } from 'react';
import './App.css';
import {getAllStudents, addStudent, updateStudent, deleteStudent} from '../../repository/myStudentApi';
import {getAllStudyPrograms, addStudyProgram, updateStudyProgram, deleteStudyProgram} from '../../repository/myStudyProgramsApi';
import StudentList from '../StudentsList/StudentsList';
import StudyProgramsList from '../StudyProgramsList/StudyProgramsList';
import AddNewStudent from "../AddNewStudent/AddNewStudent";
import AddNewStudyProgram from "../AddNewStudyProgram/AddNewStudyProgram";

class App extends Component {

    constructor(props){
        super(props);

        this.state = {
            students: [],
            studyPrograms: [],
            studentClick: false,
            studyProgramClick: false
        }
    }

    myCallback = (dataFromChild) => {
        return dataFromChild;
    };

    render() {
        return (
            <div>
                <div className="appHeader">
                    <div className="container">
                        <div className="row appLogo">
                            <div className="col-md-8 text-left">
                                Student Scheduler
                            </div>
                            <div className="col-md-4 text-right">
                                <button className="btn-header btnAll" onClick={this.onAddStudentClick}><i className="fa fa-plus-square"></i> Add Student</button>
                                <button className="btn-header btnAll" onClick={this.onAddStudyProgramClick}><i className="fa fa-plus-square"></i> Add Study Program</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="container bodyContainer">
                    {
                        (this.state.studentClick) &&
                        <div className="row componentsMarginTop">
                            <AddNewStudent studyPrograms={this.state.studyPrograms}
                                           onNewStudent={this.onNewStudent}/>
                        </div>
                    }
                    {
                        (this.state.studyProgramClick) &&
                        <div className="row componentsMarginTop">
                            <AddNewStudyProgram onNewStudyProgram={this.onNewStudyProgram}/>
                        </div>
                    }

                    <div className="row componentsMarginTop componentsMarginBottom">
                        <div className="card cardsWidth1" id="studentsListCard">
                            <div className="card-header bg-dark text-white"><h3>Students</h3></div>
                            <div className="card-body">
                                <StudentList students={this.myCallback(this.state.students)}
                                             studyPrograms={this.state.studyPrograms}
                                             onUpdateStudent={this.myCallback(this.onUpdateStudent)}
                                             onDeleteStudent={this.myCallback(this.onDeleteStudent)}/>
                            </div>
                        </div>
                        <div className="card cardsWidth2" id="studyProgramsListCard">
                            <div className="card-header bg-danger text-white"><h3>Study Programs</h3></div>
                            <div className="card-body">
                                <StudyProgramsList studyPrograms={this.myCallback(this.state.studyPrograms)}
                                                   onUpdateStudyProgram={this.myCallback(this.onUpdateStudyProgram)}
                                                   onDeleteStudyProgram={this.myCallback(this.onDeleteStudyProgram)}/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    componentDidMount() {
        this.loadData();
    }

    loadData = () => {
        getAllStudents()
            .then(response => response.json())
            .then((data) => {
                this.setState(() => ({
                    students: data
                }))
            });

        getAllStudyPrograms()
            .then(response => response.json())
            .then((data) => {
                this.setState(() => ({
                    studyPrograms: data
                }))
            });
    };

    onUpdateStudent = (student) => {
        console.log(student);
        updateStudent(student)
            .then(response=> {
                this.loadData();
            })
    };

    onNewStudent = (student) => {
        console.log(student);
        addStudent(student)
            .then(response=> {
                this.loadData();
            })
    };

    onDeleteStudyProgram = (studyProgramId) => {
        deleteStudyProgram({id:studyProgramId})
            .then(response => {
                this.loadData();
            })
    };

    onNewStudyProgram = (studyProgramName) => {
        addStudyProgram({name:studyProgramName})
            .then(response => {
                this.loadData();
            })
    };

    onUpdateStudyProgram = (studyProgram) => {
        console.log(studyProgram);
        updateStudyProgram(studyProgram)
            .then(response=> {
                this.loadData();
            })
    };

    onAddStudentClick = () => {
        this.setState({studentClick: !this.state.studentClick});
        this.setState({studyProgramClick: false});
   };


    onAddStudyProgramClick = () => {
        this.setState({studyProgramClick: !this.state.studyProgramClick});
        this.setState({studentClick: false});
    };

    onDeleteStudent = (student) => {
        deleteStudent(student)
            .then(response => {
                this.loadData();
            })
    };
}

export default App;

