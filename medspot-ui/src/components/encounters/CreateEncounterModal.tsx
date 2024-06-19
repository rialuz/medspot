import { useState } from "react";
import { createPatientEncounter } from "../helpers/apiHelper";
import { Modal, Box, Icon } from "@mui/material";
import FormHelper from "../formHelper/FormHelper";
import { useParams } from "react-router-dom";
import FormHelperTextArea from "../formHelper/FormHelperTextArea";
import "./FormStyles.css";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  minHeight: "400px",
  transform: "translate(-50%, -50%)",
  width: 600,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

function CreateEncounterModal({ open, onClose, setEncounters, setOpen }) {
  const { id } = useParams();

  const [encounter, setEncounter] = useState({
    patientId: id,
    notes: "",
    visitCode: "",
    provider: "",
    billingCode: "",
    icd10: "",
    totalCost: undefined,
    copay: undefined,
    chiefComplaint: "",
    pulse: undefined,
    systolic: undefined,
    diastolic: undefined,
    date: "",
  });

  const [errors, setErrors] = useState({});

  const addNewEncounter = (e) => {
    e.preventDefault();
    createPatientEncounter(
      encounter.patientId,
      encounter,
      setEncounters,
      setOpen,
      setErrors
    );
  };

  const onEncounterChange = (e) => {
    setEncounter({ ...encounter, [e.target.name]: e.target.value });
  };

  return (
    <div>
      <Modal open={open} onClose={onClose}>
        <Box sx={style}>
          <div onClick={() => setOpen(false)} className="close-modal">
            <Icon>clear</Icon>
          </div>

          <form onSubmit={addNewEncounter}>
            <h1 className="form-title">Create Encounter</h1>

            <div className="encounter-form">
              <FormHelper
                id="0"
                type="date"
                name="date"
                errorMessage={errors.date}
                onChange={onEncounterChange}
                label="Date"
              />

              <FormHelperTextArea
                id="1"
                label="Notes"
                name="notes"
                placeholder="Notes"
                errorMessage={errors.notes}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="2"
                label="Visit Code"
                name="visitCode"
                type="text"
                placeholder="Visit Code"
                errorMessage={errors.visitCode}
                onChange={onEncounterChange}
              />

              <FormHelperTextArea
                id="3"
                label="Chief Complaint"
                name="chiefComplaint"
                placeholder="Chief Complaint"
                errorMessage={errors.chiefComplaint}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="4"
                label="Provider"
                type="text"
                name="provider"
                placeholder="Provider"
                errorMessage={errors.provider}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="5"
                label="Billing Code"
                name="billingCode"
                type="text"
                placeholder="Billing Code"
                errorMessage={errors.billingCode}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="6"
                label="ICD10"
                name="icd10"
                type="text"
                placeholder="ICD10"
                errorMessage={errors.icd10}
                onChange={onEncounterChange}
              />
            </div>
            <div className="int-inputs">
              <div>
                <FormHelper
                  id="7"
                  type="number"
                  label="Total Cost"
                  name="totalCost"
                  step=".01"
                  errorMessage={errors.totalCost}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="8"
                  type="number"
                  label="Copay"
                  name="copay"
                  step=".01"
                  errorMessage={errors.copay}
                  onChange={onEncounterChange}
                />
              </div>

              <div>
                <FormHelper
                  id="9"
                  type="number"
                  label="Pulse"
                  name="pulse"
                  errorMessage={errors.pulse}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="10"
                  type="number"
                  label="Systolic"
                  name="systolic"
                  errorMessage={errors.systolic}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="11"
                  type="number"
                  label="Diastolic"
                  name="diastolic"
                  errorMessage={errors.diastolic}
                  onChange={onEncounterChange}
                />
              </div>
            </div>

            <div className="encounter-btn">
              <button>Create Encounter</button>
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}

export default CreateEncounterModal;
