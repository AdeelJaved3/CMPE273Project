import logo from './logo.svg';
import './App.css';

import {BrowserRouter, Route, Switch} from 'react-router-dom';
import React from 'react';
import Homepage from './components/Homepage';
import UserLogin from './components/UserLogin';
import UserSignup from './components/UserSignup';
import PatientDashboard from './components/PatientDashboard';
import EditPatientAppointment from './components/EditPatientAppointment';
import DoctorSignup from './components/DoctorSignup';
import PatientAppointmentCreation from './components/PatientAppointmentCreation';
import DoctorLogin from './components/DoctorLogin';
import DoctorDashboard from './components/DoctorDashboard';
import CreatePrescription from './components/CreatePrescription';
import DoctorPrescriptions from './components/DoctorPrescriptions';
import DoctorVaccinationCertificates from './components/DoctorVaccinationCertificates';
import PatientPrescriptions from './components/PatientPrescriptions';
import EditDoctorAppointment from './components/EditDoctorAppointment';

// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }

function App(){
  return(
    <div>
      <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Homepage}/>
        <Route exact path="/login" component={UserLogin}/>
        <Route exact path="/signup" component={UserSignup}/>
        <Route exact path="/patientDashboard" component={PatientDashboard}/>
        <Route exact path="/editPatientAppointment" component={EditPatientAppointment}/>
        <Route exact path="/doctorsignup" component={DoctorSignup}/>
        <Route exact path="/patientappointmentcreation" component={PatientAppointmentCreation}/>
        <Route exact path="/doctorlogin" component={DoctorLogin}/>
        <Route exact path="/doctordashboard" component={DoctorDashboard}/>
        <Route exact path="/createPrescription" component={CreatePrescription}/>
        <Route exact path="/doctorprescription" component={DoctorPrescriptions}/>
        <Route exact path="/doctorVaccinationCertificates" component={DoctorVaccinationCertificates}/>
        <Route exact path="/patientprescription" component={PatientPrescriptions}></Route>
        <Route exact path="/editdoctorappointment" component={EditDoctorAppointment}></Route>
      </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
