import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";
import {Routes, Route, useHistory} from 'react-router-dom'
// import DoctorVaccinationCertificates from './DoctorVaccinationCertificates';
import './styles/PatientDashboard.css'


function DoctorDashboard(){
    const history = useHistory();
    const [data, setData] = useState(null);


    useEffect(() =>{
        // axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('id')}`).then(res =>{
        axios.get(`http://localhost:8080/api/v1/appointments/users/${sessionStorage.getItem('userId')}`).then(res =>{
            setData(res.data)
            console.log(res.data)

        }).catch(err =>{
            console.log(err)
        })
    }, [])
    
    function handleButtonClick(id, patient, appointmentType, reasonForVisit){
        console.log(id)
        const patientDict = JSON.stringify(patient)
        // sessionStorage.setItem('id',id)
        sessionStorage.setItem('appointmentId',id)
        sessionStorage.setItem('patient', patientDict)
        sessionStorage.setItem('appointmentType', appointmentType)
        sessionStorage.setItem('reasonForVisit', reasonForVisit)


        window.location.href="/editdoctorappointment"
    }

    async function handleButtonDelete(id){
        let body = {
            'id' : id
        }

        await axios.delete(`http://localhost:8080/api/v1/appointments/${sessionStorage.getItem('appointmentId')}`,body).then(res =>{
            if(res.status === 200){
                window.location.href="/doctordashboard"
            }else{
                console.log("error deleting appointment doctor")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error))
        })
    }

    function handleBackToLoginClick(){
        history.push("/")
    }

    function handleAppointmentCreationClick(){
        history.push("/patientAppointmentCreation")
    }

    function handleCreatePrescriptionClick(){
        history.push("/createPrescription")
    }

    function handleDoctorPrescriptionsClick(){
        history.push("/doctorprescription")
    }

    function handleVaccinationClick(){
        history.push("/doctorVaccinationCertificates")
    }

    return(
        <div>
            <h1>Doctor Dashboard</h1>
            <Table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Patient Name</th>
                        <th>Appointment Type</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Vaccine</th>
                        <th>Dose #</th>
                    </tr>
                </thead>
                <tbody>
                    {data && data.map(item =>(
                        <tr>
                            <td>{item.id}</td>
                            <td>{item.patient.firstName} {item.patient.lastName}</td>
                            <td>{item.appointmentType}</td>
                            <td>{item.date}</td>
                            <td>{item.time}</td>
                            <td>{item.vaccine.name}</td>
                            <td>{item.doseNumber}</td>
                            {/* <td><Button onClick={handleButtonClick.bind(this, item.id, item.appointmentType, item.vaccine.name, item.doseNumber)}>Edit Appointment</Button></td>
                            <td><Button onClick={handleButtonDelete.bind(this, item.id)}>Delete Appointment</Button></td> */}
                            
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Button onClick={handleBackToLoginClick}>Sign Out</Button>
            {/* <Button onClick={handleAppointmentCreationClick}>Create an Appointment</Button> */}
            
            <Button onClick={handleDoctorPrescriptionsClick}>Show Prescriptions Given</Button>
            <Button onClick={handleVaccinationClick}>Create Certificate</Button>
            <Button onClick={handleCreatePrescriptionClick}>Create Prescription</Button>
        </div>
    )
}

export default DoctorDashboard;