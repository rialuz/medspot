/* eslint-disable @typescript-eslint/no-explicit-any */
import { Icon } from "@mui/material";
import { Link } from "react-router-dom";
import { Fragment } from "react/jsx-runtime";
import "../MainContent.css"
import { useState } from "react";
import PatientFormModal from "../patients/modals/PatientFormModal";

function PatientTable ({patients, setPatients}) {
    const [open, setOpen] = useState(false);

    const viewAddPatientForm = () => setOpen(true);
    const closePatientForm = (reason:any) =>{
        if (reason && reason === "backdropClick")
            return;
        setOpen(false)
    };

    return (
    <div>
        <div className="table-container">
           <table className="patient-table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Email</th>            
                    <td className="add-patient">
                        <button onClick={viewAddPatientForm}>
                            <Icon>add</Icon><p>Patient</p>                           
                        </button>
                    </td>   
                </tr>                
            </thead>

            <tbody>
                {
                    patients.map((patient:any) => (
                        <Fragment key={patient.id}>
                            <tr>
                                <td>{patient.firstName} {patient.lastName}</td>
                                <td>{patient.age}</td>
                                <td>{patient.email}</td> 
                                <td className="view-patient-column">
                                    <div className="view-patient-container">
                                        <p>View Patient</p>
                                        <Link to={`/patients/${patient.id}`} className="link">
                                        <Icon>north_east</Icon>
                                        </Link>
                                    </div>
                                </td>                                
                            </tr>  
             
                        </Fragment>

                    ))
                }
            </tbody>

          </table> 
        </div>

        <PatientFormModal open={open} onClose={closePatientForm} setPatients={setPatients} setOpen={setOpen} />
        
    </div>
    )
}

export default PatientTable;