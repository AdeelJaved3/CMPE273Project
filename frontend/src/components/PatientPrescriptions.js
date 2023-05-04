import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import axios from 'axios';
import React, {useState, useEffect} from "react";
import {Routes, Route, useHistory} from 'react-router-dom'

function PatientPrescriptions(){
    const history = useHistory();
    const [prescription, setPrescription] = useState([])



    useEffect(()=>{
        axios.get(`http://localhost:8080/api/v1/prescriptions/users/${sessionStorage.getItem('userId')}`).then(res =>{
            console.log(res)
            setPrescription(res.data)
        }).catch(err =>{
            console.log(err)
        })
    },[])


    function handleBackToDashboardClick(){
        history.push("/patientDashboard")
    }

    return(
        <div>
            <Table>
                <thead>
                    <tr>
                        <th>Medication</th>
                        <th>Dosage</th>
                        <th>Instructions</th>
                        <th>Date Prescribed</th>
                    </tr>
                </thead>
                <tbody>
                    {prescription.map((item)=>(
                        <tr>
                            <td>{item.medication}</td>
                            <td>{item.dosage}</td>
                            <td>{item.instructions}</td>
                            <td>{item.datePrescribed}</td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            <Button onClick={handleBackToDashboardClick}>Go back to Patient Dashboard</Button>
        </div>
    )
}

export default PatientPrescriptions;