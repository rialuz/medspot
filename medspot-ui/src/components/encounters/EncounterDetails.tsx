import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getPatientEncounterById } from "../helpers/apiHelper";
import { Icon } from "@mui/material";
import "../patients/PatientInfo.css"
import UpdateEncounterModal from "./UpdateEncounterModal";

function EncounterDetails() {
  const [encounter, setEncounter] = useState({});
  const { id, encounterId } = useParams();

  const [updateModal, setUpdateModal] = useState(false);

  useEffect(() => {
    getPatientEncounterById(id, encounterId, setEncounter);
  }, []);

  const closeUpdateModal = () => {
    setUpdateModal(false);
  }

  return (
    <div className="info-page">
      <div className="redirect-btn">
        <Link to={`/patients/${id}`}>
          <button>Go Back</button>
        </Link>
      </div>

      <div className="patient-info-container">
        <div className="patient-details">
          <div className="icons">
            <Icon onClick={() => setUpdateModal(true)}>edit</Icon>
          </div>

          <div className="encounters-detail">
            <div>
              <label>Date:</label>
              {encounter.date}
            </div>
            <div>
              <label>Notes:</label>
              {
                encounter.notes ?
                (
                  <p>{encounter.notes}</p>
                ) : (
                  'N/A'
                )
              }
            </div>
            <div>
              <label>Visit Code:</label> {encounter.visitCode}
            </div>
            <div>
              <label>Provider:</label> {encounter.provider}
            </div>
            <div>
              <label>Billing Code:</label> {encounter.billingCode}
            </div>
            <div>
              <label>ICD10:</label> {encounter.icd10}
            </div>
            <div>
              <label>Total Cost:</label>${encounter.totalCost}{" "}
            </div>
            <div>
              <label>Copay:</label>${encounter.copay}{" "}
            </div>
            <div>
              <label>Chief Complaint:</label> {encounter.chiefComplaint}{" "}
            </div>
            <div>
              <label>Pulse:</label>
              {
                encounter.pulse ?
                (
                  <p>{encounter.pulse}</p>
                ) : (
                  'N/A'
                )
              }
            </div>
            <div>
              <label>Blood Pressure:</label>
              {
                encounter.systolic && encounter.diastolic ?
                (
                  <p>{encounter.systolic} / {encounter.diastolic}</p>
                ) : (
                  'N/A'
                )
              }
            </div>
          </div>
        </div>
      </div>
      <UpdateEncounterModal
        open={updateModal}
        onClose={closeUpdateModal}
        setOpen={setUpdateModal}
        encounter={encounter}
        setEncounter={setEncounter}
      />
    </div>
  );
}

export default EncounterDetails;
