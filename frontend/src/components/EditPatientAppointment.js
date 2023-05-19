import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";

// function EditPatientAppointment(){
//     return(
//         <div>
//             edit patient appointments
//         </div>
//     )
// }
// export default EditPatientAppointment;


export default class EditPatientAppointment extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            // id : sessionStorage.getItem('id'),
            id : sessionStorage.getItem('appointmentId'),
            appointmentType: sessionStorage.getItem('appointmentType'),
            date : sessionStorage.getItem('date'),
            time : sessionStorage.getItem('time')
        }

        this.handleInputChange = this.handleInputChange.bind(this)
    }

    handleInputChange(event){
        const {name, value} = event.target;
        this.state[name] = value;
        console.log(this.state)
    }

    handleButtonClicked = async () => {
        console.log(this.state)
        let body = this.state

        // await axios.put(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('id')}`,body).then(res => {
        await axios.put(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('appointmentId')}`,body).then(res => {
            console.log(res.data);

            if(res.status === 200 || res.status === 201){
                window.location.href= "/patientDashboard"
            } else{
                console.log("error has occurred")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }

    render(){
        return (
            <div>
                <h1>Edit Patient Appointment</h1>
                <form>
                    <h3>
                        <label>ID</label>
                        {/* <input type="text" name="id" onChange={this.handleInputChange} defaultValue={sessionStorage.getItem('id')}></input> */}
                        <input type="text" name="id" onChange={this.handleInputChange} defaultValue={this.state.id}></input>
                    </h3>


                    <h3>
                        <label>Appointment Type</label>
                        <input type="text" name="appointmentType" onChange={this.handleInputChange}defaultValue={sessionStorage.getItem('appointmentType')}/>
                    </h3>

                    <h3>
                        <label>Date</label>
                        <input type="text" name="date" onChange={this.handleInputChange}defaultValue={sessionStorage.getItem('date')}/>
                    </h3>

                    <h3>
                        <label>Time</label>
                        <input type="text" name="time" onChange={this.handleInputChange}defaultValue={sessionStorage.getItem('time')}/>
                    </h3>
                </form>
                <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
            </div>
        )
    }
}