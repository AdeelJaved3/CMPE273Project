import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";
import {Routes, Route, useHistory} from 'react-router-dom'
import './styles/PatientDashboard.css'

// const getData = async (data) => {
//     const res = await axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`)
//     return res
// }

function PatientDashboard(){
    const history = useHistory();
    const [data, setData] = useState(null)

    useEffect(()=>{
        // console.log(sessionStorage.getItem('id'))
        console.log(sessionStorage.getItem('userId'))
        // axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`).then(res => {
        axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('userId')}`).then(res => {
            setData(res.data)
            // sessionStorage.setItem('apps', res.data)
            console.log(res.data)
            // console.log(data)
        }).catch (err => {
            console.log(err)
        })
        // async function getApps() {
        //     const { apps }= await axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`)
        //     setAppData(apps)
        //     // await axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`).then(res =>{
        //     //     console.log(res)
        //     //     setApp(res.data)
        //     //     //console.log(res.data)
        //     //     console.log(app)
        //     // }).catch(err =>{
        //     //     console.log(err)
        //     // })
        // }

        // getApps()

        // console.log(data)
        // console.log(sessionStorage.getItem('apps'))
        // console.log(sessionStorage.getItem('apps')[0])
    }, [])

    // function handleButtonClick(id, appointmentType, date, time, {doctor}, {patient}){
    function handleButtonClick(id, appointmentType, date, time){
        console.log(id)
        // sessionStorage.setItem('id', id)
        sessionStorage.setItem('appointmentId', id)
        sessionStorage.setItem('appointmentType', appointmentType)
        sessionStorage.setItem('date', date)
        sessionStorage.setItem('time', time)
        // sessionStorage.setItem('doctorId', doctor.id)
        // sessionStorage.setItem('patientId', patient.id)

        console.log(id)
        window.location.href="/editpatientappointment"
        
    }

    async function handleButtonDelete(appointmentId){
        // console.log(id)

        // let body = {
        //     'id' : id
        // }


        // console.log(`appointment id: ${sessionStorage.getItem('appointmentId')}`)
        console.log(`appointment id: ${appointmentId}`)

        // await axios.delete(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('id')}`,body).then(res =>{
        // await axios.delete(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('appointmentId')}`,body).then(res =>{

        // await axios.delete(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('appointmentId')}`).then(res =>{
        await axios.delete(`http://localhost:8080/api/v1/appointments/${appointmentId}`).then(res =>{
            console.log(res.data);
            console.log(res.status)

            if(res.status === 200){
                // console.log(this.state)
                console.log("Appointment successfully cancelled")
                window.location.href="/patientDashboard"
            }
            else{
                console.log("delete error")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }

    function handleBackToHomeClick(){
        history.push("/")
    }

    function handlePrescriptionClick(){
        // console.log("button click")
        history.push("/patientprescription")
    }

    function handleAppointmentCreationClick(){
        history.push("/patientAppointmentCreation")
    }



    return(
        <div>
            <h1>Patient Dashboard</h1>
            <Table>
                <thead className = "header"> 
                    <tr>
                        <th>No #</th>
                        <th>Dr. Name</th>
                        <th>Dr. Email</th>
                        <th>Dr. Phone #</th>
                        <th>Type</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Reason For Visit</th>
                        <th>Vaccine</th>
                        <th>Status</th>
                        <th>Edit Option</th>
                        <th>Cancel Option</th>
                    </tr>
                </thead>
                <tbody>
                    {/* {data.map(item => {
                        <tr>
                            <td>{item}</td>
                        </tr>
                    })} */}
                    {data && data.map(item => (
                        <tr>
                            <td>{item.id}</td>
                            <td>{item.doctor.firstName + ' ' + item.doctor.lastName}</td>
                            <td>{item.doctor.email}</td>
                            <td>{item.doctor.phoneNumber}</td>
                            <td>{item.appointmentType}</td>
                            <td>{item.date}</td>
                            <td>{item.time}</td>
                            <td>{item.reasonForVisit}</td>
                            <td>{item.vaccine.name}</td>
                            <td>{item.status}</td>
                            <td><Button onClick={handleButtonClick.bind(this, item.id, item.appointmentType, item.date, item.time)}>Edit</Button></td>
                            <td><Button onClick={handleButtonDelete.bind(this, item.id)}>Cancel</Button></td>
                        </tr>
                    ))}
                    {/* {appData.map((item) =>{
                        <tr>
                            <td>{item.doctor.firstName}</td>
                        </tr>
                     })} */}
                </tbody>
            </Table>
            <Button onClick={handleAppointmentCreationClick}>Create an Appointment</Button>
            <Button onClick={handleBackToHomeClick}>Sign out</Button>
            <Button onClick={handlePrescriptionClick}>Show My Presciptions</Button>
        </div>
    )
}

export default PatientDashboard;




// export default class PatientDashboard extends React.Component{
//     constructor(props){
//         super(props);
//         this.state={}

//         axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`).then(res =>{
//             this.state.data = res.data
            
//             console.log(this.state.data)
//         })
//     }

    
    
//     render(){
//         //const {state} = this.props.location
//         console.log(sessionStorage.getItem('id'))
//         console.log(`state: ${this.state.data}`)
//         return(
//             <div>
//              <Table>
//                  <thead>
//                      <tr>
//                          <th>Doctor Name</th>
//                          <th>Doctor Email</th>
//                          <th>Doctor Phone Number</th>
//                          <th>Appointment Type</th>
//                          <th>Date</th>
//                          <th>Time</th>
//                          <th>Reason For Visit</th>
//                          <th>Vaccine</th>
//                          <th>Status</th>
//                      </tr>
//                  </thead>
//                  <tbody>
//                     {/* {this.state.data.map((item) =>{
//                         <tr>
//                             <td>{item.doctor.firstName}</td>
//                         </tr>
//                     })} */}
//                  </tbody>
//              </Table>
//          </div>
//         )
//     }
    
// }