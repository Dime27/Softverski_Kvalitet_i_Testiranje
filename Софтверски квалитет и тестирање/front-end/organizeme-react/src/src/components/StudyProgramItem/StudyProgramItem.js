import React, { Component } from 'react';
import './StudyProgramItem.css';

class StudyProgramItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            studyProgramEditClick: false
        }
    }

    deleteStudyProgram = () => {
        this.props.onDeleteStudyProgram(this.props.studyProgram.id);
    };

    onStudyProgramClick = () => {
        this.setState({studyProgramEditClick: !this.state.studyProgramEditClick});
    };

    onFormSubmit = (e) => {
        e.preventDefault();
        console.log(this.props.studyProgram.id);
        console.log(e.target.studyProgram.value);
        this.props.onUpdateStudyProgram({
            id: this.props.studyProgram.id,
            name: e.target.studyProgram.value
        });
        this.setState({studyProgramEditClick: false});
    };

    render(){
        return(
            <li className="list-group-item" >
                <div className="row">
                    <div className="col-md-4 text-center studyProgramItemNames">{this.props.studyProgram.name}</div>
                    <div className="col-md-4 text-center"><button type={"button"} onClick={this.deleteStudyProgram} className="btn-delete btnAll"><i className="fa fa-trash"></i> Delete</button></div>
                    <div className="col-md-4 text-center"><button type={"button"} onClick={this.onStudyProgramClick} className="btn-primary btn-edit btnAll"><i className="fa fa-edit"></i> Edit</button></div>
                </div>
                {
                    (this.state.studyProgramEditClick) &&
                    <form onSubmit={this.onFormSubmit}>
                        <div className="wrapper">
                            <div className="row">
                                <div className="col-md-6">
                                    <input name={"studyProgram"} type={"text"} className={"form-control"} placeholder={this.props.studyProgram.name} />
                                </div>
                                <div className="col-md-6">
                                    <button type={"submit"} className="btn-submit btnAll">Submit</button>
                                </div>
                            </div>
                        </div>
                    </form>
                }
            </li>
        );
    }
}

export default StudyProgramItem;

