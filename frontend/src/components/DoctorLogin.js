import React, { Component } from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import axios from 'axios';


export default class DoctorLogin extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            username : '',
            password: ''
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
        const {name, value} = event.target;
        this.state[name] = value;
        console.log(this.state)
    }

    handleButtonClicked = async (id) =>{
        let body = this.state
    
        await axios.post("http://localhost:8080/api/v1/auth/login",body).then(res =>{
          console.log(res.data)
    
          if(res.status === 200 || res.status === 201){
            // sessionStorage.setItem('id', res.data.id)
            sessionStorage.setItem('userId', res.data.id)
            window.location.href = "/doctordashboard"
            
          }
          else{
            console.log("login error")
          }
        }).catch(error =>{
          console.log(JSON.stringify(error));
        })
      }

    render() {
    return(
        <div>
        <form>
            <h1>Doctor Sign In</h1>

            <h3>
                <label className='username'>Username: </label>
                <input className='inputusername' type="text" name="username" onChange={this.handleInputChange}/>
            </h3>
            <h3>
                <label className='password'>Password: </label>
                <input className='inputpassword' type="text" name="password" onChange={this.handleInputChange}/>
            </h3>


        </form>
        <button onClick={this.handleButtonClicked.bind(this)}>Submit</button>
        </div>
    )
    }

}




// function DoctorLogin(){
//     return(
//         <div>doctor login</div>
//     )
// }

// export default DoctorLogin;