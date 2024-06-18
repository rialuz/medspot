import { Box, Icon, Modal } from "@mui/material";
import FormHelper from "../../formHelper/FormHelper";
import { useState } from "react";
import { addPatient } from "../../helpers/apiHelper";
import "./PatientFormModal.css";
import StateSelect from "../../formHelper/StateSelect";
import GenderSelect from "../../formHelper/GenderSelect";

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

function PatientFormModal({ open, onClose, setPatients, setOpen }) {
  const [patient, setPatient] = useState({
    firstName: "",
    lastName: "",
    ssn: "",
    email: "",
    age: undefined,
    height: undefined,
    weight: undefined,
    insurance: "",
    gender: "",
    street: "",
    city: "",
    state: "",
    postal: "",
  });

  const [errors, setErrors] = useState({});

  const addNewPatient = (e) => {
    e.preventDefault();
    addPatient(patient, setPatients, setErrors, setOpen);
  };

  const onPatientChange = (e) => {
    setPatient({ ...patient, [e.target.name]: e.target.value });
  };

  return (
    <div>
      <Modal open={open} onClose={onClose}>
        <Box sx={style}>
          <div onClick={() => setOpen(false)} className="close-modal">
            <Icon>clear</Icon>
          </div>

          <form onSubmit={addNewPatient}>
            <h1 className="form-title">Add Patient</h1>
            <div className="add-patient-form">
              <FormHelper
                id="1"
                label="First Name"
                name="firstName"
                type="text"
                placeholder="First Name"
                errorMessage={errors.firstName}
                onChange={onPatientChange}
              />

              <FormHelper
                id="2"
                label="Last Name"
                type="text"
                name="lastName"
                placeholder="Last Name"
                errorMessage={errors.lastName}
                onChange={onPatientChange}
              />

              <FormHelper
                id="3"
                label="Email"
                type="text"
                name="email"
                placeholder="Email"
                r
                errorMessage={errors.email}
                onChange={onPatientChange}
              />

              <FormHelper
                id="4"
                label="Social Security Number"
                type="text"
                name="ssn"
                errorMessage={errors.ssn}
                placeholder="Social Security Number"
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
                  onChange={onPatientChange}
                />

                <FormHelper
                  id="6"
                  label="Height(in.)"
                  type="number"
                  name="height"
                  placeholder="Height"
                  errorMessage={errors.height}
                  onChange={onPatientChange}
                />

                <FormHelper
                  id="7"
                  label="Weight(lbs.)"
                  type="number"
                  name="weight"
                  placeholder="Weight"
                  errorMessage={errors.weight}
                  onChange={onPatientChange}
                />
              </div>

              <FormHelper
                id="8"
                label="Insurance"
                type="text"
                name="insurance"
                placeholder="Insurance"
                errorMessage={errors.insurance}
                onChange={onPatientChange}
              />

              <GenderSelect
                label="Gender"
                name="gender"
                placeholder="Gender"
                errorMessage={errors.gender}
                onChange={onPatientChange}
              />

              <FormHelper
                id="10"
                label="Street"
                type="text"
                name="street"
                placeholder="Street"
                errorMessage={errors.street}
                onChange={onPatientChange}
              />

              <FormHelper
                id="11"
                label="City"
                type="text"
                name="city"
                placeholder="City"
                errorMessage={errors.city}
                onChange={onPatientChange}
              />

              <StateSelect
                onChange={onPatientChange}
                errorMessage={errors.state}
              />

              <FormHelper
                id="13"
                label="Postal"
                type="text"
                name="postal"
                placeholder="Postal"
                errorMessage={errors.postal}
                onChange={onPatientChange}
              />
            </div>

            <div className="patient-btn">
              <button>Add Patient</button>
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}

export default PatientFormModal;
