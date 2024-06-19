import { Icon, Box, Modal } from "@mui/material";
import FormHelper from "../formHelper/FormHelper";
import FormHelperTextArea from "../formHelper/FormHelperTextArea";
import { useState } from "react";
import { updatePatientEncounter } from "../helpers/apiHelper";
import { validateRequiredEncounterFields } from "./EncounterValidation";

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

function UpdateEncounterModal({
  open,
  onClose,
  encounter,
  setEncounter,
  setOpen,
}) {
const [errors, setErrors] = useState({});

const onEncounterChange = (e) => {
  setEncounter({...encounter, [e.target.name]: e.target.value});
}

const updateEncounter = (e) => {
  e.preventDefault();
  updatePatientEncounter(encounter.patientId, encounter.id, encounter, setOpen, setErrors);
  setErrors(validateRequiredEncounterFields(encounter))
}


  return (
    <div>
      <Modal open={open} onClose={onClose}>
        <Box sx={style}>
          <div onClick={() => setOpen(false)} className="close-modal">
            <Icon>clear</Icon>
          </div>

          <form onSubmit={updateEncounter}>
            <h1 className="form-title">Update Encounter</h1>

            <div className="encounter-form">
              <FormHelper
                id="0"
                type="date"
                name="date"
                errorMessage={errors.date}
                defaultVal={encounter.date}
                onChange={onEncounterChange}
                label="Date"
              />

              <FormHelperTextArea
                id="1"
                label="Notes"
                name="notes"
                placeholder="Notes"
                errorMessage={errors.notes}
                defaultVal={encounter.notes}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="2"
                label="Visit Code"
                name="visitCode"
                type="text"
                placeholder="Visit Code"
                defaultVal={encounter.visitCode}
                errorMessage={errors.visitCode}
                onChange={onEncounterChange}
              />

              <FormHelperTextArea
                id="3"
                label="Chief Complaint"
                name="chiefComplaint"
                placeholder="Chief Complaint"
                errorMessage={errors.chiefComplaint}
                defaultVal={encounter.chiefComplaint}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="4"
                label="Provider"
                type="text"
                name="provider"
                placeholder="Provider"
                errorMessage={errors.provider}
                defaultVal={encounter.provider}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="5"
                label="Billing Code"
                name="billingCode"
                type="text"
                placeholder="Billing Code"
                errorMessage={errors.billingCode}
                defaultVal={encounter.billingCode}
                onChange={onEncounterChange}
              />

              <FormHelper
                id="6"
                label="ICD10"
                name="icd10"
                type="text"
                placeholder="ICD10"
                errorMessage={errors.icd10}
                defaultVal={encounter.icd10}
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
                  defaultVal={encounter.totalCost}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="8"
                  type="number"
                  label="Copay"
                  name="copay"
                  step=".01"
                  errorMessage={errors.copay}
                  defaultVal={encounter.copay}
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
                  defaultVal={encounter.pulse}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="10"
                  type="number"
                  label="Systolic"
                  name="systolic"
                  errorMessage={errors.systolic}
                  defaultVal={encounter.systolic}
                  onChange={onEncounterChange}
                />

                <FormHelper
                  id="11"
                  type="number"
                  label="Diastolic"
                  name="diastolic"
                  errorMessage={errors.diastolic}
                  defaultVal={encounter.diastolic}
                  onChange={onEncounterChange}
                />
              </div>
            </div>

            <div className="encounter-btn">
              <button>Update Encounter</button>
            </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}

export default UpdateEncounterModal;
