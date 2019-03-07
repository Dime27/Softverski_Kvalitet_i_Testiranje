import React, { Component } from 'react';
import './AddNewStudyProgram.css'

class AddNewStudyProgram extends Component{
    constructor(props){
        super(props);
    }

    onFormSubmit = (e) => {
        e.preventDefault();

        this.props.onNewStudyProgram(e.target.studyProgramName.value);
    };

    render(){
        return(
            <form onSubmit={this.onFormSubmit} id="formAddNewStudyProgram">
                <div className="wrapper">
                    <div className="row">
                        <div className="col-md-4">
                            <input name={"studyProgramName"} type={"text"} className={"form-control"} placeholder="Enter study program..."/>
                        </div>

                        <div className="col-md-6">
                            <button type={"submit"} className="btn-submit btnAll">Submit</button>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

export default AddNewStudyProgram;