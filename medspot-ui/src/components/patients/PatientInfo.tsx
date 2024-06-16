
// getPatientBYID using url params set patientInfo

import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"
import { getPatientById, getPatientEncounters } from "../helpers/apiHelper";
import '../MainContent.css';
import './PatientInfo.css'
import { Icon } from "@mui/material";
import DeletePatientModal from "./modals/DeletePatientModal";
import PatientUpdateForm from "./modals/PatientUpdateForm";


function PatientInfo () {
    const [patient, setPatient] = useState({});
    const [encounters, setEncounters] = useState([]);
    const [viewMore, setViewMore] = useState(false);
    const [openDeleteModal, setDeleteModal] = useState(false);
    const [openUpdateModal, setUpdateModal] = useState(false);

    const { id } = useParams();

    useEffect(() => {
        getPatientById(id, setPatient);
        getPatientEncounters(id, setEncounters);
    }, [])


    const toggleViewMore = () => {
        setViewMore(!viewMore);
        console.log('toggle', viewMore)
    }

    const closeDeleteModal = (reason:any) => {
        if (reason && reason === "backdropClick")
            return;
        setDeleteModal(false)
    }

    const closeUpdateModal = (reason:any) => {
        if (reason && reason === "backdropClick")
            return;
        setUpdateModal(false)
    }


    return (
            <div className="patient-info-page">
                <div className="redirect-btn">
                    <Link to="/">
                    <button>Go Back</button>
                    </Link>                    
                </div>

                <div className="patient-info-container">
                    <div className="patient-details">
                        <div className="icons">
                            <Icon onClick={() => setUpdateModal(true)}>edit</Icon>
                            <Icon onClick={() => setDeleteModal(true)}>delete_forever</Icon>
                        </div>
                        
                        <div>
                        <div><label>Name:</label> { patient.firstName } { patient.lastName }</div>
                        <div><label>Age:</label> { patient.age }</div>
                        <div><label>SSN:</label> { patient.ssn }</div>
                        <div><label>Email:</label> { patient.email }</div>
                        <div><label>Height(in.):</label> { patient.height }</div>
                        <div><label>Weight(lbs):</label> { patient.weight } </div>
                        <div><label>Insurance:</label> { patient.insurance } </div>
                        <div><label>Gender:</label> { patient.gender } </div>
                        <div><label>Address:</label> { patient.street } { patient.city }, { patient.state }, { patient.postal }</div>
                        </div>

                                                
                    </div>

                    <div className="table-container">
                        <table className="patient-table">
                            <thead>
                            <tr className="table-header-row">
                                    <th>Date</th>
                                    <th>Visit Code</th>
                                    <th>Provider</th>
                                    <th></th>
                                </tr>
                            </thead>
                                {
                                    encounters.map((encounter) => (
                                        <tbody className="table-body-row" key={encounter.id}>
                                            <tr>
                                                <td>{encounter.date}</td>
                                                <td>{encounter.visitCode}</td>
                                                <td>{encounter.provider}</td>
                                                <td className="view-more">View Details<Icon className="icon" onClick={toggleViewMore}>expand_more</Icon></td>
                                            </tr>

                                            { viewMore ? 
                                            (
                                              <tr>
                                                <td colSpan={4}>
                                            <div className="encounters-detail">
                                                <div><label>Date:</label>{ encounter.date }</div>
                                                <div><label>Notes:</label> { encounter.notes }</div>
                                                <div><label>Visit Code:</label> { encounter.visitCode }</div>
                                                <div><label>Provider:</label> { encounter.provider }</div>
                                                <div><label>Billing Code:</label> { encounter.billingCode }</div>
                                                <div><label>ICD10:</label> { encounter.icd10 }</div>
                                                <div><label>Total Cost:</label>${ encounter.totalCost } </div>
                                                <div><label>Copay:</label>${ encounter.copay } </div>
                                                <div><label>Chief Complaint:</label> { encounter.chiefComplaint } </div>
                                                <div><label>Pulse:</label>{ encounter.pulse }</div>
                                                <div><label>Blood Pressure:</label>{ encounter.systolic } / { encounter.diastolic }</div>
                                            </div>                                                
                                                </td>
                                            </tr>  
                                            ) 
                                            :
                                            (
                                                <></>
                                            )
                                            }
                                        </tbody>                                   
                                    ))
                                }
                                                
                        </table>

                    </div>
                </div>    
                <DeletePatientModal
                    id={patient.id}
                    open={openDeleteModal}
                    onClose={closeDeleteModal}
                    setOpen={setDeleteModal}

                />  

                <PatientUpdateForm
                    open={openUpdateModal}
                    onClose={closeUpdateModal}
                    setOpen={setUpdateModal}
                    patient={patient}
                    setPatient={setPatient}
                />              
            </div>
                
    )
}

export default PatientInfo