import React, { Component } from 'react';
import './EditStudentDetails.css';

class EditStudentDetails extends Component {
    constructor(props){
        super(props);

        this.state = {
            editClick: false
        }
    }

    onFormSubmit = (e) => {
        e.preventDefault();

        this.props.onUpdateStudent(
            {
                name: e.target.studentIme.value,
                lastName: e.target.studentPrezime.value,
                index: e.target.studentIndex.value,
                studyProgram: {
                    name: e.target.studentNasoka.value
                }
            }
        );

        this.props.onStudentClickClose();
    };

    editClickChange = () => {
        this.setState({editClick: true});
    };

    render(){
        let studyPrograms = this.props.studyPrograms.map((item, index) => {
            return (
                <option value={item.name}>{item.name}</option>
            )
        });

        return(
            <form onSubmit={this.onFormSubmit}>
                <div className="wrapper">
                    {
                        (!this.state.editClick) &&
                        <div className="row" onClick={this.onStudentClick}>
                            <div className="col-md-3 text-center">{this.props.student.name}</div>
                            <div className="col-md-3 text-center">{this.props.student.lastName}</div>
                            <div className="col-md-2 text-center studentDetailsIndexes">{this.props.student.index}</div>
                            <div className="col-md-2 text-center studentDetailsStudyProgramsNames">{this.props.student.studyProgram.name}</div>
                            <div className="col-md-2 text-center col3NoPadding"><button type={"button"} onClick={this.editClickChange} className="btn-primary btn-edit btnAll"><i className="fa fa-edit"></i> Edit</button></div>
                        </div>
                    }

                    {
                        (this.state.editClick) &&
                        <div className="row">
                            <div className="col-md-3 editStudentColumns">
                                <input name={"studentIme"} type={"text"} className={"form-control"} placeholder={this.props.student.name}/>
                            </div>
                            <div className="col-md-3 editStudentColumns">
                                <input name={"studentPrezime"} type={"text"} className={"form-control"} placeholder={this.props.student.lastName} />
                            </div>
                            <div className="col-md-2 editStudentColumns">
                                <input name={"studentIndex"} type={"text"} className={"form-control"} value={this.props.student.index} disabled={"disabled"}/>
                            </div>
                            <div className="col-md-2 editStudentColumns">
                                {/*<input name={"studentNasoka"} type={"text"} className={"form-control"} placeholder={this.props.student.studyProgram.name} />*/}
                                <select name="studentNasoka" className="form-control" required>
                                    {studyPrograms}
                                </select>
                            </div>
                            <div className="col-md-1">
                                <button type={"submit"} className="btn-submit btnAll">Submit</button>
                            </div>
                        </div>
                    }
                </div>
            </form>
        )
    }
}

export default EditStudentDetails;