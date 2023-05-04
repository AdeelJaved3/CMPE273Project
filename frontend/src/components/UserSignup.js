import React, { Component, useState } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import axios from 'axios';


export default class UserSignup extends React.Component {
    //Setting the state for the initial launch
    constructor(props){
        super(props);
        this.state= {
            basicDetails: {
                firstName:'',
                lastName:'',
                email:'',
                phoneNumber:'',
                gender:'',
                dateOfBirth:'',
                role:'',
                userName:'',
                password:'',
                dateCreated: new Date()
            },
                address : '',
                allergies : '',
                additionalInfo : '',
                familyHistory : '',
                insuranceNumber : '',
                dateCreated: new Date()
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

    //On button click, makes call to backend API to add a new patient to the database
    handleButtonClicked = async () => {
        let body = this.state

        await axios.post("http://localhost:8080/api/v1/users/patients", body).then(res =>{
            console.log(res.data)

            if(res.status === 200 || res.status === 201){
                window.location.href="/"
              }
              else {
                console.log("error has occcured")
              }
            }).catch(error =>{
              console.log(JSON.stringify(error));
        });
    }








  render() {
    return(
       <div>
        <h1>Sign Up for Patient</h1>
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
                <label className='address'>Address: </label>
                <input className='inputAddress' type="text" name="address" onChange={this.handleInputChange}/>
            </h3>
            <h3>
                <label className='allergies'>Allergies: </label>
                <input className='inputallergies' type="text" name="allergies" onChange={this.handleInputChange}/>
            </h3>
            <h3>
                <label className='additionalInfo'>Additional Information:  </label>
                <input className='inputadditionalInfo' type="text" name="additionalInfo" onChange={this.handleInputChange}/>
            </h3>
            <h3>
                <label className='familyHistory'>Family History </label>
                <input className='inputfamilyHistory' type="text" name="familyHistory" onChange={this.handleInputChange}/>
            </h3>
            <h3>
                <label className='insuranceNumber'>Insurance Number:  </label>
                <input className='inputinsuranceNumber' type="text" name="insuranceNumber" onChange={this.handleInputChange}/>
            </h3>
            
        </form>
        <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
       </div>
    )
  }
}





{/* <TextField label='firstName' placeholder='Enter first name' variant="outlined" fullWidth required/>
                <TextField label='lastName' placeholder='Enter last name' variant="outlined" fullWidth required/>
                <TextField label='email' placeholder='Enter email' variant="outlined" fullWidth required/>
                <TextField label='phoneNumber' placeholder='Enter phone number' variant="outlined" fullWidth required/>
                <TextField label='gender' placeholder='Enter gender' variant="outlined" fullWidth required/>
                <TextField label='dateOfBirth' placeholder='Enter date of birth' variant="outlined" fullWidth required/>
                <TextField label='role' placeholder='Enter role' variant="outlined" fullWidth required/>
                <TextField label='Username' placeholder='Enter username' variant="outlined" fullWidth required/>
                <TextField label='Password' placeholder='Enter password' type='password' variant="outlined" fullWidth required/>
                <TextField label='dateCreated' placeholder='Enter date created' variant="outlined" fullWidth required/> */}




// function UserSignup(){
//     const[firstName, setFirstName] = useState("");

//     let res = await fetch()

//     return(
//         <Grid>
//             <Paper>
//                 <Grid>
//                     <h2>Sign Up for patient</h2>
//                 </Grid>
//                 <TextField label='firstName' placeholder='Enter first name' variant="outlined" fullWidth required/>
//                 <TextField label='lastName' placeholder='Enter last name' variant="outlined" fullWidth required/>
//                 <TextField label='email' placeholder='Enter email' variant="outlined" fullWidth required/>
//                 <TextField label='phoneNumber' placeholder='Enter phone number' variant="outlined" fullWidth required/>
//                 <TextField label='gender' placeholder='Enter gender' variant="outlined" fullWidth required/>
//                 <TextField label='dateOfBirth' placeholder='Enter date of birth' variant="outlined" fullWidth required/>
//                 <TextField label='role' placeholder='Enter role' variant="outlined" fullWidth required/>
//                 <TextField label='Username' placeholder='Enter username' variant="outlined" fullWidth required/>
//                 <TextField label='Password' placeholder='Enter password' type='password' variant="outlined" fullWidth required/>
                
               
//                 <Button type='submit' color='primary' variant="contained">Register</Button>
                
//                 <Typography> Already have an account?
//                      <Link href="/login">Sign In </Link>
//                 </Typography>
//             </Paper>
//         </Grid>
//     )
// }

// export default UserSignup;