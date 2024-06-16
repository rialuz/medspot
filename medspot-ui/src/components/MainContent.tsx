/* eslint-disable @typescript-eslint/no-explicit-any */
import { Fragment } from "react/jsx-runtime"
import './MainContent.css'
import { Icon } from "@mui/material"
import { Link } from "react-router-dom"
import PatientTable from "./patients/PatientTable"

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
  
    return (
        <div className='main-content'>
          <h1>Patients</h1>
          <PatientTable patients={patients} setPatients={setPatients} />
        </div>
    )
  }
  
  export default MainContent