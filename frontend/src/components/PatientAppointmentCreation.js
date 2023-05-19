import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, { useEffect } from 'react';
import { Grid, Paper, Avatar, TextField, Typography,Link } from '@material-ui/core'

// function PatientAppointmentCreation(){
//     return(
//         <div>patient appointment creation</div>
//     )
// }


// export default PatientAppointmentCreation;

export default class PatientAppointmentCreation extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            doctor : {
                'id' : ''
            },
            patient : {
                'id' : sessionStorage.getItem('userId')
            },
            appointmentType : '',
            date : '',
            time : '',
            reasonForVisit : '',
            vaccine : {
                'id' : ''
            },
            // doseNumber : '',
            // nextDoseDate : ''
        };
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    //On input change, it updates the state to the new value
    handleInputChange(event) {
        const {name, value} = event.target;
        // this.state[name] = value;
        // this.state.doctor[name] = value;
        // this.state.patient[name] = value;
        // this.state.vaccine[name] = value;
        //console.log(this.state)
        //if (name === this.state.doctor.id || name === this.state.patient.id || name === this.state.vaccine.id) {

    //     if (name === 'doctorId' || name === 'patientId' || name === 'vaccineId') {
    //         this.setState(prevState => ({
    //             [name]: {
    //                 ...prevState[name],
    //                 id: value
    //             },
    //             [name.slice(0, -2)]: {
    //                 ...prevState[name.slice(0, -2)],
    //                 id: value
    //               }
    //         }));
    //     } else{
    //         this.setState({[name]: value});
    //     }
    //     console.log(this.state)
    // }

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
    }}

    //     if (name === 'doctorId') {
    //         this.setState(prevState => ({
    //             doctor :{
    //                 id : value
    //             },
    //             [name.slice(0, -2)]: {
    //                 ...prevState[name.slice(0, -2)],
    //                 id: value
    //               }
    //         }));
    //     }else if(name === 'patientId' || name === 'vaccineId'){
    //         this.setState(prevState =>({
    //             [name]: {
    //                 id : value
    //             },
    //             [name.slice(0, -2)]: {
    //                 ...prevState[name.slice(0, -2)],
    //                 id: value
    //             }
    //         }));
    //     } else{
    //         this.setState({[name]: value});
    //     }
    //     console.log(this.state)
    // }


    //WORKING WITH -1 CHARACTER ERROR
    // handleInputChange(event) {
    //     const { name, value } = event.target;
    //     this.setState(prevState => {
    //       if (name === 'doctorId') {
    //         return {
    //           doctor: {
    //             id: value
    //           },
    //           [name]: value,
    //           [name.slice(0, -2)]: {
    //             ...prevState[name.slice(0, -2)],
    //             id: value
    //           }
    //         };
    //       } else if (name === 'patientId' || name === 'vaccineId') {
    //         return {
    //           [name]: {
    //             id: value
    //           },
    //           [name.slice(0, -2)]: {
    //             ...prevState[name.slice(0, -2)],
    //             id: value
    //           }
    //         };
    //       } else {
    //         return { 
    //           [name]: value 
    //         };
    //       }
    //     });
    //     console.log(this.state)
    //   }
    handleButtonClicked = async () =>{
        let body = this.state

        await axios.post("http://localhost:8080/api/v1/appointments/",body).then(res =>{
            console.log(res.data)

            if(res.status === 200 || res.status === 201) {
                window.location.href="/patientDashboard"
            }
            else{
                console.log("error on patient appointment creation")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }

    render(){
        const paperStyle={display: 'flex', flexDirection: 'column', justifyContent: 'center',padding :20,height:'40vh',width:350, margin:"20px auto"}
        return(
            <div className='background'>
                <Paper elevation={10} style={paperStyle}>
            <div>
                <h1>Patient Appointment Creation</h1>
                <form>
                    <h3>
                        <label>Doctor ID: </label>
                        <input type="text" name="doctorId" value={this.state.doctor.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Patient ID: </label>
                        <input type="text" name="patientId" value={this.state.patient.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Appointment Type: </label>
                        {/* <input type="text" name="appointmentType" value={this.state.appointmentType} onChange={this.handleInputChange}></input> */}
                        <input list = "appointments" name ="appointmentType" value={this.state.appointmentType} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Date: </label>
                        <input type="text" name="date" value={this.state.date} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Time for Appointment: </label>
                        <input type="text" name="time" value={this.state.time} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Reason for Visit:  </label>
                        <input type="text" name="reasonForVisit" value={this.state.reasonForVisit} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Vaccine ID:  </label>
                        <input type="text" name="vaccineId" value={this.state.vaccine.id} onChange={this.handleInputChange}></input>
                    </h3>

                    <datalist id = "appointments">
                        <option value = "VACCINATION"></option>
                        <option value = "DOCTOR"></option>
                    </datalist>

                    {/* <h3>
                        <label>Dose Number:  </label>
                        <input type="text" name="doseNumber" value={this.state.doseNumber} onChange={this.handleInputChange}></input>
                    </h3>

                    <h3>
                        <label>Next Dose Date:  </label>
                        <input type="text" name="nextDoseDate" value={this.state.nextDoseDate} onChange={this.handleInputChange}></input>
                    </h3> */}
                </form>
                <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
            </div>
            </Paper>
            </div>
        )
    }
}