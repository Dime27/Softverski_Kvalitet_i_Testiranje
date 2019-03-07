import React, { Component } from 'react';

class AddNewStudent extends Component{
    constructor(props){
        super(props);
    }

    onFormSubmit = (formSubmitEvent) => {
        formSubmitEvent.preventDefault();
        console.log('onFormSubmit', formSubmitEvent);

        this.props.onNewStudent(
            {
                index: (formSubmitEvent.target.studentIndex.value),
                name: formSubmitEvent.target.studentIme.value,
                lastName: formSubmitEvent.target.studentLastName.value,
                studyProgram: {
                    name: formSubmitEvent.target.studyProgramsSelect.value
                }

            }
        );
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
                    <div className="row">
                        <div className="col-md-3">
                            <input name={"studentIndex"} type={"text"} className={"form-control"} placeholder="Enter index"/>
                        </div>
                        <div className="col-md-3">
                            <input name={"studentIme"} type={"text"} className={"form-control"} placeholder="Enter name"/>
                        </div>
                        <div className="col-md-3">
                            <input name={"studentLastName"} type={"text"} className={"form-control"} placeholder="Enter last name"/>
                        </div>
                        <div className="col-md-2">
                            <select name="studyProgramsSelect" className="form-control" required>
                                {studyPrograms}
                            </select>
                        </div>
                        <div className="col-md-1">
                            <button type={"submit"} className="btn-submit btnAll">Submit</button>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

export default AddNewStudent;