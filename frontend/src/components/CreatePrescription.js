import React, { Component } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import axios from 'axios';



// function CreatePrescription(){
//     return(
//         <div>create prescription</div>
//     )
// }

// export default CreatePrescription;

export default class CreatePrescription extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            patient : {
                // id : ''
                id : sessionStorage.getItem('patientId')
            },
            doctor : {
                // id : ''
                // id : sessionStorage.getItem('doctorId')
                id : sessionStorage.getItem('userId')
            },
            medication : '',
            dosage : '',
            instructions : '',
            // datePrescribed : ''
            datePrescribed : new Date()
        };
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const {name, value} = event.target;

        if (name === 'doctorId' || name === 'patientId') {
            this.setState(prevState => ({
                [name]: {
                    ...prevState[name],
                    id: value
                },
                [name.slice(0, -2)]: {
                    ...prevState[name.slice(0, -2)],
                    id: value
                  }
            }), () => {
                console.log(this.state);
            });
        } else{
            this.setState({[name]: value}, () => {
                console.log(this.state);
            });
        }
    }


    handleButtonClicked = async () =>{
        let body = this.state;

        await axios.post("http://localhost:8080/api/v1/prescriptions/",body).then(res =>{
            console.log(res.data);

            if(res.status === 200 || res.status === 201) {
                window.location.href="/doctorDashboard"
                //change this href?
            }
            else{
                console.log("error on patient appointment creation")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }

    render(){
        return(
            <div>
                <h1>Create Prescription</h1>
                <form>
                    <h3>
                        <label>Patient ID </label>
                        <input type="text" name="patientId" value={this.state.patient.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Doctor ID </label>
                        <input type="text" name="doctorId" value={this.state.doctor.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Medication </label>
                        <input type="text" name="medication" value={this.state.medication} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Dosage </label>
                        <input type="text" name="dosage" value={this.state.dosage} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Instructions </label>
                        <input type="text" name="instructions" value={this.state.instructions} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Date Prescribed </label>
                        <input type="text" name="datePrescribed" value={this.state.datePrescribed} onChange={this.handleInputChange}></input>
                    </h3>
                </form>
                <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
            </div>
        )
    }
}