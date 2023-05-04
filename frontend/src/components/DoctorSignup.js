import React, { Component, useState } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import axios from 'axios';

export default class DoctorSignup extends React.Component {
    constructor(props){
        super(props);

        this.state = {
            basicDetails :{
                firstName : '',
                lastname : '',
                email : '',
                phoneNumber : '',
                gender : '',
                dateOfBirth : '',
                userName : '',
                role : '',
                password :'',
                dateCreated : new Date()
            },
            experience : '',
            specialization : '',
            qualification : '',
            regNo : '',
            startTime : '',
            endTime : '',
            dateCreated : new Date()
        };
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    //On input change, it updates the state to the new value
    handleInputChange(event) {
        const {name, value} = event.target;
        this.state[name] = value;
        this.state.basicDetails[name] = value;
        console.log(this.state)
    }

    handleButtonClicked = async () =>{
        let body = this.state

        await axios.post("http://localhost:8080/api/v1/users/doctors",body).then (res => {
            console.log(res.data)

            if(res.status === 200 || res.status === 201) {
                window.location.href="/"
            }
            else{
                console.log("error on doctor signup")
            }
        }).catch(error =>{
            console.log(JSON.stringify(error));
        });
    }



    render(){
        return(
            <div>
                <h1>Doctor Sign Up</h1>
                <form>
                    <h3>
                        <label className='firstName'>First Name: </label>
                        <input className='inputfirstname' type="text" name="firstName" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='lastName'>Last Name: </label>
                        <input className='inputlastname' type="text" name="lastName" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='email'>Email: </label>
                        <input className='inputemail' type="text" name="email" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='phoneNumber'>Phone Number: </label>
                        <input className='inputphonenumber' type="text" name="phoneNumber" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='gender'>Gender: </label>
                        <input className='inputgender' type="text" name="gender" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='dateOfBirth'>Date Of Birth: </label>
                        <input className='inputdateofbirth' type="text" name="dateOfBirth" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='role'>Role: </label>
                        <input className='inputrole' type="text" name="role" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='userName'>Username: </label>
                        <input className='inputusername' type="text" name="userName" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='password'>Password: </label>
                        <input className='inputpassword' type="text" name="password" onChange={this.handleInputChange}/>
                    </h3>

                    <h3>
                        <label className='experience'>Experience: </label>
                        <input className='inputexperience' type="text" name="experience" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='specialization'>Specialization: </label>
                        <input className='inputspecialization' type="text" name="specialization" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='qualification'>Qualification: </label>
                        <input className='inputqualification' type="text" name="qualification" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='regNo'>License Number: </label>
                        <input className='inputregNo' type="text" name="regNo" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='startTime'>Start Time: </label>
                        <input className='inputstartTime' type="text" name="startTime" onChange={this.handleInputChange}/>
                    </h3>
                    <h3>
                        <label className='endTime'>End Time: </label>
                        <input className='inputendTime' type="text" name="endTime" onChange={this.handleInputChange}/>
                    </h3>
                </form>
                <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
            </div>
        )
    }
}