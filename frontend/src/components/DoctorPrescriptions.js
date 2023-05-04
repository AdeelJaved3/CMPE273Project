import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";
import {Routes, Route, useHistory} from 'react-router-dom'

function DoctorPrescriptions(){
    const history = useHistory();
    const [prescriptions, setPrescription] = useState([])

    useEffect(() =>{
        axios.get(`http://localhost:8080/api/v1/prescriptions/users/${sessionStorage.getItem('userId')}`).then(res =>{
            console.log(res)
            setPrescription(res.data)
        }).catch(err =>{
            console.log(err)
        })
    },[])



    function handleBackToDoctorDashboardClick(){
        history.push("/doctordashboard")
    }

    return(
        <div>
            <h1>All Prescriptions Given to my Patients</h1>
            <Table>
                <thead>
                    <tr>
                        <th>Patient Name</th>
                        <th>Medication</th>
                        <th>Dosage</th>
                        <th>Instructions</th>
                        <th>Date Prescribed</th>
                    </tr>
                </thead>
                <tbody>
                    {prescriptions.map((item) =>(
                        <tr>
                            <td>{item.patient.firstName} {item.patient.lastName}</td>
                            <td>{item.medication}</td>
                            <td>{item.dosage}</td>
                            <td>{item.instructions}</td>
                            <td>{item.datePrescribed}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Button onClick={handleBackToDoctorDashboardClick}>Back to Doctor Dashboard</Button>
        </div>
    )
}

export default DoctorPrescriptions;