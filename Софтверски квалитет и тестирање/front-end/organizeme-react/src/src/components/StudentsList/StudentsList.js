import React, {Component} from 'react';
import StudentItem from '../StudentItem/StudentItem';

class StudentsList extends Component {
    constructor(props){
        super(props);
    }

    myCallback = (dataFromChild) => {
        return dataFromChild;
    };

    render(){
        const studentsList = this.props.students.map((student, index) => {
            return (
                <StudentItem index={index} student={this.myCallback(student)} studyPrograms={this.props.studyPrograms}
                             onUpdateStudent={this.myCallback(this.props.onUpdateStudent)}
                             onDeleteStudent={this.myCallback(this.props.onDeleteStudent)}/>
            );
        });
        return(
            <div className="col-md-12">
                <br/>
                {studentsList}
            </div>
        );
    }
}

export default StudentsList;