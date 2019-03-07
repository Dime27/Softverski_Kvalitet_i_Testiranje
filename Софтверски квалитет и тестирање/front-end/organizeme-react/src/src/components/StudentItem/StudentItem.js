import React, { Component } from 'react';
import EditStudentDetails from '../EditStudentDetails/EditStudentDetails';

class StudentItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            studentClick: false
        }
    }

    deleteStudent = () => {
        this.props.onDeleteStudent(this.props.student);
        this.setState({studentClick: false});
    };


    onStudentClick = () => {
        this.setState({studentClick: !this.state.studentClick});
    };

    /*onStudentClick = (e) => {
        this.setState((state, props) => {
            return {
                studentClick: !this.state.studentClick
            }
        });
    };*/

    myCallback = (dataFromChild) => {
        return dataFromChild;
    };

    render(){
        return(
            <li className="list-group-item" >
                <div className="row" onClick={this.onStudentClick}>
                    <div className="col-md-4 text-center studentItemNames">{this.props.student.name}</div>
                    <div className="col-md-5 text-center studentItemLastNames">{this.props.student.lastName}</div>
                    <div className="col-md-3 text-center"><button type={"button"} onClick={this.deleteStudent} className="btn-delete btnAll"><i className="fa fa-trash"></i> Delete</button></div>
                </div>
                {
                    (this.state.studentClick) &&
                    <div className="row">
                        <div className="col-md-12">
                            <br/>
                            <EditStudentDetails index={this.props.index}
                                                student={this.myCallback(this.props.student)}
                                                onStudentClickClose={this.onStudentClick}
                                                studyPrograms={this.props.studyPrograms}
                                                onUpdateStudent={this.myCallback(this.props.onUpdateStudent)}/>
                        </div>
                    </div>
                }
            </li>
        );
    }
}

export default StudentItem;

