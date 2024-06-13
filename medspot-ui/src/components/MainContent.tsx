/* eslint-disable @typescript-eslint/no-explicit-any */
import { Fragment } from "react/jsx-runtime"
import { client } from "../client"
import './MainContent.css'

/**
 * Make POST request - Add Patient  +
 * 
 * Ideally
 * This will have a table full of patients with
 * patient minimal patient data
 *  - name, dob, email, phone number
 * Click Arrow to view more patient data
 * New page with table of patient more data - 
 * here you can update / delete patient - 
 * once patient is deleted, redirect user 
 * to patient screen
 * encounters table - should be able to edit + add encounter
 * @returns 
 */
function MainContent({patients, setPatients}) { // passing from parent to child add in object ({ props})

    const addPatient = (patient: any) => {
        client
        .post('/patients', patient)
        .then((res) => {
            setPatients((patients: any) => [res.data, ...patients])
        })
    }
  
    return (
        <div className='main-content'>
          <h1>Patients</h1>
        
        <div className="table-container">
           <table className="patient-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Email</th>               
                </tr>                
            </thead>

            <tbody>
                {
                    patients.map((patient:any) => (
                        <Fragment>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>                            <tr key={patient.id}>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td>                                 
                            </tr>
             
                        </Fragment>

                    ))
                }
            </tbody>

          </table> 
        </div>
          
        </div>
    )
  }
  
  export default MainContent