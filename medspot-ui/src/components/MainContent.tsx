/* eslint-disable @typescript-eslint/no-explicit-any */
import './MainContent.css'
import PatientTable from "./patients/PatientTable"

function MainContent({patients, setPatients}) { // passing from parent to child add in object ({ props})
  
    return (
        <div className='main-content'>
          <h1>Patients</h1>
          <PatientTable patients={patients} setPatients={setPatients} />
        </div>
    )
  }
  
  export default MainContent