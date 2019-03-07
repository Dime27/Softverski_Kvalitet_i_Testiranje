import React, {Component} from 'react';
import StudyProgramItem from '../StudyProgramItem/StudyProgramItem';

class StudyProgramsList extends Component {
    constructor(props){
        super(props);
    }

    myCallback = (dataFromChild) => {
        return dataFromChild;
    };

    render(){
        const StudyProgramsList = this.props.studyPrograms.map((studyProgram, index) => {
            return (
                <StudyProgramItem index={index}
                                  studyProgram={this.myCallback(studyProgram)}
                                  onUpdateStudyProgram={this.myCallback(this.props.onUpdateStudyProgram)}
                                  onDeleteStudyProgram={this.myCallback(this.props.onDeleteStudyProgram)}/>
            );
        });
        return(
            <div className="col-md-12">
                <br/>
                {StudyProgramsList}
            </div>
        );
    }
}

export default StudyProgramsList;