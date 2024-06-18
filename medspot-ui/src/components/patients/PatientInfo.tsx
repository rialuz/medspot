import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getPatientById, getPatientEncounters } from "../helpers/apiHelper";
import "../MainContent.css";
import "./PatientInfo.css";
import { Icon } from "@mui/material";
import DeletePatientModal from "./modals/DeletePatientModal";
import PatientUpdateForm from "./modals/PatientUpdateForm";
import EncountersTable from "../encounters/EncountersTable";

function PatientInfo() {
  const [patient, setPatient] = useState({});
  const [encounters, setEncounters] = useState([]);
  const [openDeleteModal, setDeleteModal] = useState(false);
  const [openUpdateModal, setUpdateModal] = useState(false);

  const { id } = useParams();

  useEffect(() => {
    getPatientById(id, setPatient);
    getPatientEncounters(id, setEncounters);
  }, []);

  const closeDeleteModal = (reason: any) => {
    if (reason && reason === "backdropClick") return;
    setDeleteModal(false);
  };

  const closeUpdateModal = (reason: any) => {
    if (reason && reason === "backdropClick") return;
    setUpdateModal(false);
  };

  return (
    <div className="info-page">
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
            <div>
              <label>Name:</label> {patient.firstName} {patient.lastName}
            </div>
            <div>
              <label>Age:</label> {patient.age}
            </div>
            <div>
              <label>SSN:</label> {patient.ssn}
            </div>
            <div>
              <label>Email:</label> {patient.email}
            </div>
            <div>
              <label>Height(in.):</label> {patient.height}
            </div>
            <div>
              <label>Weight(lbs):</label> {patient.weight}{" "}
            </div>
            <div>
              <label>Insurance:</label> {patient.insurance}{" "}
            </div>
            <div>
              <label>Gender:</label> {patient.gender}{" "}
            </div>
            <div>
              <label>Address:</label> {patient.street} {patient.city},{" "}
              {patient.state}, {patient.postal}
            </div>
          </div>
        </div>
      </div>
      <EncountersTable patient={patient} encounters={encounters} setEncounters={setEncounters} />
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
  );
}

export default PatientInfo;
