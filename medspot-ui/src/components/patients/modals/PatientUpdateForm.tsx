import { Modal, Box, Icon } from "@mui/material";
import FormHelper from "../../formHelper/FormHelper";
import StateSelect from "../../formHelper/StateSelect";
import { useState } from "react";
import { updatePatient } from "../../helpers/apiHelper";
import { validateRequiredFields } from "../PatientValidation";

const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    minHeight: "500px",
    transform: "translate(-50%, -50%)",
    width: 600,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };

function PatientUpdateForm({open, onClose, patient, setPatient, setOpen}) {

    const [errors, setErrors] = useState({});

    const onPatientChange = (e) => {
        setPatient({ ...patient, [e.target.name]: e.target.value })
    }

    const updatePatientFunc = async (e) => {
        e.preventDefault();
        updatePatient(patient.id, patient,setErrors, setOpen);
        setErrors(validateRequiredFields(patient));
    }

    return (
        <div>
            <Modal
            open={open}
            onClose={onClose}
            >
                <Box sx={style}>
                    <div onClick={() => setOpen(false)} className="close-modal">
                       <Icon>clear</Icon> 
                    </div>
                    
                    <form onSubmit={updatePatientFunc}>
                        <h1 className="form-title">Update Patient</h1>
                    <div className="add-patient-form">
                        <FormHelper 
                       id="1"
                       label="First Name"
                       name="firstName"
                       type="text"
                       placeholder="First Name"
                       errorMessage={errors.firstName}
                       defaultVal={patient.firstName}
                       onChange={onPatientChange} />

                       <FormHelper 
                       id="2"
                       label="Last Name"
                       type="text"
                       name="lastName"
                       placeholder="Last Name"
                       errorMessage={errors.lastName}
                       defaultVal={patient.lastName}
                       onChange={onPatientChange}
                       />

                        <FormHelper 
                       id="3"
                       label="Email"
                       type="text"
                       name="email"
                       placeholder="Email"r
                       errorMessage={errors.email}
                       defaultVal={patient.email}
                       onChange={onPatientChange} />

                       <FormHelper 
                       id="4"
                       label="Social Security Number"
                       type="text"
                       name="ssn"
                       errorMessage={errors.ssn}
                       placeholder="Social Security Number"
                       defaultVal={patient.ssn}
                       onChange={onPatientChange}
                       />

                    <div className="integer-inputs">
                        <FormHelper 
                       id="5"
                       label="Age"
                       type="number"
                       name="age"
                       placeholder="Age"
                       errorMessage={errors.age}
                       defaultVal={patient.age}
                       onChange={onPatientChange} />

                       <FormHelper 
                       id="6"
                       label="Height(in.)"
                       type="number"
                       name="height"
                       placeholder="Height"
                       errorMessage={errors.height}
                       defaultVal={patient.height}
                       onChange={onPatientChange}
                       />

                       <FormHelper 
                       id="7"
                       label="Weight(lbs.)"
                       type="number"
                       name="weight"
                       placeholder="Weight"
                       errorMessage={errors.weight}
                       defaultVal={patient.weight}
                       onChange={onPatientChange} />
                    </div>
                       

                       <FormHelper 
                       id="8"
                       label="Insurance"
                       type="text"
                       name="insurance"
                       placeholder="Insurance"
                       errorMessage={errors.insurance}
                       defaultVal={patient.insurance}
                       onChange={onPatientChange}
                       />

                        <FormHelper 
                       id="9"
                       label="Gender"
                       name="gender"
                       placeholder="Gender"
                       errorMessage={errors.gender}
                       defaultVal={patient.gender}
                       onChange={onPatientChange} />

                       <FormHelper 
                       id="10"
                       label="Street"
                       type="text"
                       name="street"
                       placeholder="Street"
                       errorMessage={errors.street}
                       defaultVal={patient.street}
                       onChange={onPatientChange}
                       />

                       <FormHelper 
                       id="11"
                       label="City"
                       type="text"
                       name="city"
                       placeholder="City"
                       errorMessage={errors.city}
                       defaultVal={patient.city}
                       onChange={onPatientChange} />

                       <StateSelect
                       onChange={onPatientChange}
                       errorMessage={errors.state}
                       defaultVal={patient.state}
                       />

                       <FormHelper 
                       id="13"
                       label="Postal"
                       type="text"
                       name="postal"
                       placeholder="Postal"
                       errorMessage={errors.postal}
                       defaultVal={patient.postal}
                       onChange={onPatientChange}
                       />
                    </div>           

                    <div className="patient-btn">
                        <button >Update Patient</button>
                    </div>
                       
                    </form>
                    
                </Box>

            </Modal>
        </div>
    )
}


export default PatientUpdateForm;