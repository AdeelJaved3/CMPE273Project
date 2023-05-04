import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";

// function DoctorVaccinationCertficates(){
//     return(
//         <div>Doctor Vaccinations</div>
//     )
// }

// export default DoctorVaccinationCertficates;


export default class DoctorVaccinationCertificates extends React.Component{
    constructor(props){
        super(props);

        // this.state = {
        //     patient : {
        //         id : ''
        //     },
        //     doctor : {
        //         id : ''
        //     },
        //     vaccine : {
        //         id : ''
        //     },
        //     doseNumber : '',
        //     dateAdministered : '',
        //     nextDoseDate : ''
        // };

        this.state = {
            appId : ''
        }

        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const {name, value} = event.target;

        if (name === 'doctorId' || name === 'patientId' || name === 'vaccineId') {
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

        await axios.post("http://localhost:8080/api/v1/patient-vaccinations/",body).then(res =>{
            console.log(res.data);

            if(res.status === 200 || res.status === 201) {
                window.location.href="/doctorDashboard"
                //change this href?
            }
            else{
                console.log("error on patient vaccination creation")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }

    render(){
        return(
            <div>
                <h1>Vaccination Certificate </h1>
                <form>
                    <h3>
                        <label>Appointment ID </label>
                        <input type="text" name="appId" value={this.state.appId} onChange={this.handleInputChange}></input>
                    </h3>
                    {/* <h3>
                        <label>Patient ID </label>
                        <input type="text" name="patientId" value={this.state.patient.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Doctor ID </label>
                        <input type="text" name="doctorId" value={this.state.doctor.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Vaccine ID</label>
                        <input type="text" name="vaccineId" value={this.state.vaccine.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Dose Number</label>
                        <input type="text" name="doseNumber" value={this.state.doseNumber} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Date Administered</label>
                        <input type="text" name="dateAdministered" value={this.state.dateAdministered} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Next Dose Date</label>
                        <input type="text" name="nextDoseDate" value={this.state.nextDoseDate} onChange={this.handleInputChange}></input>
                    </h3> */}
                </form>
                <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
            </div>
        )
    }
}