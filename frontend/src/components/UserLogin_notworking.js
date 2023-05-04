// import React, { useState } from 'react';
// import { Form, Button } from 'react-bootstrap';


// const UserLogin = (props) => {
//   const [state, setState] = useState({
//     username:'',
//     password:''
//   });


//   const handleButtonClicked = (event) => {
//     event.preventDefault();
//     props.history.push({
//       pathname: '/patientDashboard',
//       state
//     });
//   };

//   const handleInputChange = (event) =>{
//     const {name, value} = event.target;
//     setState((prevState) => ({
//       ...prevState,
//       [name]: value
//     }));
//   };

//   return(
//     <div>
//       <h1>Patient Log In</h1>
//       <Form className ="userlogin" onSubmit={handleButtonClicked}>
//         <Form.Group controlId= 'username'>
//           <Form.Label>User Name</Form.Label>
//           <Form.Control 
//             type="text"
//             placeholder= "Enter username"
//             name="username"
//             onChange={handleInputChange}/>
//         </Form.Group>

//         <Form.Group controlId= 'password'>
//           <Form.Label>User Name</Form.Label>
//           <Form.Control 
//             type="text"
//             placeholder= "Enter passsword"
//             name="username"
//             onChange={handleInputChange}/>
//         </Form.Group>
//         <Button variant="primary" type="submit"> Log In </Button>
//       </Form>
//     </div>
//   );
// };

// export default UserLogin;