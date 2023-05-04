import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button'
import Dropdown from 'react-bootstrap/Dropdown';
import { Grid,Paper, Avatar} from '@material-ui/core';
import axios from 'axios';
import {Routes, Route, useHistory} from 'react-router-dom'
// import LocalHospitalSharpIcon from '@mui/icons-material/LocalHospitalSharp';
// import './styles/Homepage.css';
import background from './styles/backgroundimg.jpeg'



function Homepage(){
    const history = useHistory();

    function handlePatientLoginClick(){
        history.push("/login")
    }
    function handlePatientSignUpClick(){
        history.push("/signup")
    }
    function handleDoctorSignUpClick(){
        history.push("/doctorsignup")
    }
    function handleDoctorLogInClick(){
        history.push("/doctorlogin")
    }
    const paperStyle={display: 'flex', flexDirection: 'column', justifyContent: 'center',padding :20,height:'40vh',width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#1bbd7d'}
    const btnstyle={margin:'8px 0'}
    return(
        <div style ={{backgroundImage: `url(${background})`, backgroundRepeat: 'no-repeat', height:'2000px'}}>
            <Paper elevation={10} style={paperStyle}> 
                <Grid align ='center'> <h1>Welcome to Health Connect</h1>
                <Avatar style={avatarStyle}></Avatar>
                    <div className='homepage'>
                        <Button onClick={handlePatientLoginClick}>Log In </Button>
                        <Button onClick={handlePatientSignUpClick}>Patient Sign Up</Button>
                        {/* <Button onClick={handleDoctorLogInClick}>Doctor Log In</Button> */}
                        <Button onClick={handleDoctorSignUpClick}>Doctor Sign Up</Button>
                    </div>
                </Grid>
            </Paper>
        </div>
    )
}
export default Homepage;