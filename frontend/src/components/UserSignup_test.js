// import React, { Component, useState } from 'react'
// import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
// import axios from 'axios';


// export default class UserSignup extends Component {
//     state = {
//         firstName:'',
//         lastName:'',
//         email:'',
//         phoneNumber:'',
//         gender:'',
//         dateOfBirth:'',
//         role:'',
//         username:'',
//         password:'',
//         dateCreated:''
//     }
//     handleSubmit = event =>{
//         event.preventDefault();
//         const user = {
//             firstName : this.state.firstName,
//             lastName : this.state.lastName,
//             email : this.state.email,
//             phoneNumber : this.state.phoneNumber,
//             gender : this.state.gender,
//             dateOfBirth : this.state.dateOfBirth,
//             role : this.state.role,
//             username : this.state.username,
//             password : this.state.password,
//             dateCreated : this.state.dateCreated
//         }
//         axios.post('http://localhost:8080/api/v1/users/patients', {user}).then(res =>{
//             console.log(res);
//             console.log(res.data);
//             window.location = "/login" //This line of code will redirect you once the submission is succeed
//         })
//     }
//     handleChange = event =>{
//         this.setState({
//             firstName : event.target.value,
//             lastName : event.target.value,
//             email : event.target.value,
//             phoneNumber : event.target.value,
//             gender : event.target.value,
//             dateOfBirth : event.target.value,
//             role : event.target.value,
//             username : event.target.value,
//             password : event.target.value,
//             dateCreated : event.target.value
//         });
//         console.log(event)
//     }
//   render() {
//     return(
//         <Grid>
//             <Paper>
//                 <Grid>
//                     <h2>Sign Up for patient</h2>
//                 </Grid>
                // <form onSubmit = {this.handleSubmit}>
                //     <label>First Name
                //         <input type = "text" firstName = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Last Name
                //         <input type = "text" lastName = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Email
                //         <input type = "text" email = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Phone Number
                //         <input type = "text" phoneNumber = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Gender
                //         <input type = "text" gender = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Date Of Birth
                //         <input type = "text" dateOfBirth = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Role
                //         <input type = "text" role = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Username
                //         <input type = "text" username = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Password
                //         <input type = "text" password = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <label>Date Created
                //         <input type = "text" dateCreated = "firstName" onChange={this.handleChange}/>
                //     </label>
                //     <Button type='submit' color='primary' variant="contained">Register</Button>
                // </form>
                
                
               
                
                
//                 <Typography> Already have an account?
//                      <Link href="/login">Sign In </Link>
//                 </Typography>
//             </Paper>
//         </Grid>
//     )
//   }
// }


// {/* <TextField label='firstName' placeholder='Enter first name' variant="outlined" fullWidth required/>
//                 <TextField label='lastName' placeholder='Enter last name' variant="outlined" fullWidth required/>
//                 <TextField label='email' placeholder='Enter email' variant="outlined" fullWidth required/>
//                 <TextField label='phoneNumber' placeholder='Enter phone number' variant="outlined" fullWidth required/>
//                 <TextField label='gender' placeholder='Enter gender' variant="outlined" fullWidth required/>
//                 <TextField label='dateOfBirth' placeholder='Enter date of birth' variant="outlined" fullWidth required/>
//                 <TextField label='role' placeholder='Enter role' variant="outlined" fullWidth required/>
//                 <TextField label='Username' placeholder='Enter username' variant="outlined" fullWidth required/>
//                 <TextField label='Password' placeholder='Enter password' type='password' variant="outlined" fullWidth required/>
//                 <TextField label='dateCreated' placeholder='Enter date created' variant="outlined" fullWidth required/> */}




// // function UserSignup(){
// //     const[firstName, setFirstName] = useState("");

// //     let res = await fetch()

// //     return(
// //         <Grid>
// //             <Paper>
// //                 <Grid>
// //                     <h2>Sign Up for patient</h2>
// //                 </Grid>
// //                 <TextField label='firstName' placeholder='Enter first name' variant="outlined" fullWidth required/>
// //                 <TextField label='lastName' placeholder='Enter last name' variant="outlined" fullWidth required/>
// //                 <TextField label='email' placeholder='Enter email' variant="outlined" fullWidth required/>
// //                 <TextField label='phoneNumber' placeholder='Enter phone number' variant="outlined" fullWidth required/>
// //                 <TextField label='gender' placeholder='Enter gender' variant="outlined" fullWidth required/>
// //                 <TextField label='dateOfBirth' placeholder='Enter date of birth' variant="outlined" fullWidth required/>
// //                 <TextField label='role' placeholder='Enter role' variant="outlined" fullWidth required/>
// //                 <TextField label='Username' placeholder='Enter username' variant="outlined" fullWidth required/>
// //                 <TextField label='Password' placeholder='Enter password' type='password' variant="outlined" fullWidth required/>
                
               
// //                 <Button type='submit' color='primary' variant="contained">Register</Button>
                
// //                 <Typography> Already have an account?
// //                      <Link href="/login">Sign In </Link>
// //                 </Typography>
// //             </Paper>
// //         </Grid>
// //     )
// // }

// // export default UserSignup;