/* eslint-disable @typescript-eslint/no-explicit-any */
// http requests
import { client } from "../../client"
import { validateRequiredFields } from "../patients/PatientValidation";

//Get patients
export const getPatients = (setPatients: any) => {
    client.get('/patients')
    .then((res) => {
      setPatients(res.data);
    })
    .catch((err) => console.log(err))
  }

//Get patients by ID
export const getPatientById = (id:any, setPatient: any) => {
    client.get(`/patients/${id}`)
    .then((res) => {
      setPatient(res.data);
    })
    .catch((err) => console.log(err))
  }

//POST patients
export const addPatient = (patient: any, setPatients: any, setErrors: any, setOpen: any) => {
    client
    .post('/patients', patient)
    .then((res) => {
        setPatients((patients: any) => [res.data, ...patients])
        if(res.status === 201) setOpen(false);
    })
    .catch((err) => {
      setErrors(validateRequiredFields(patient));
    })
}

// UPDATE patients
export const updatePatient = (patientId:any, patient:any, setErrors:any, setOpen:any) => {
  client
  .put( `/patients/${patientId}`, patient)
  .then((res) => {
      if(res.status === 200) setOpen(false);
  })
  .catch((err) => {
  })
}

// DELETE patients
export const deletePatient = (patientId:any, redirect: any, setErrorMessage:any) => {
  client
  .delete(`/patients/${patientId}`)
  .then((res) => {
    if (res.status === 204) {
      redirect();
    }
})
.catch((err) => {
  setErrorMessage(true)
})
}

// GET patient Encounters
export const getPatientEncounters = (id:any, setEncounters:any) => {
    client.get(`/patients/${id}/encounters`)
    .then((res) => {
      setEncounters(res.data);
    })
    .catch((err) => console.log(err))
}

// Get patient Encounters by id
export const getPatientEncounterById = (id:any, encounterId:any, setEncounter:any) => {
    client.get(`/patients/${id}/encounters/${encounterId}`)
    .then((res) => {
      setEncounter(res.data);
    })
    .catch((err) => console.log(err))
}

// Update patient encounters
export const updatePatientEncounter = () => {

}
