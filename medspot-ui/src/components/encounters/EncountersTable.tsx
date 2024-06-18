import { Icon } from "@mui/material";
import { Fragment } from "react/jsx-runtime";
import "../MainContent.css";
import { useState } from "react";
import CreateEncounterModal from "./CreateEncounterModal";
import { Link } from "react-router-dom";

const EncountersTable = ({ patient, encounters, setEncounters }) => {
  const [openCreateModal, setOpenCreateModal] = useState(false);

  const closeCreateModal = () => {
    setOpenCreateModal(false);
  };

  return (
    <div>
      <h1 className="encounters-title">Encounters</h1>
      <div className="table-container">
        <table className="patient-table">
          <thead>
            <tr className="table-header-row">
              <th>Date</th>
              <th>Visit Code</th>
              <th>Provider</th>
              <td className="add-patient">
                <button onClick={() => setOpenCreateModal(true)}>
                  <Icon>add</Icon>
                  <p>Encounter</p>
                </button>
              </td>
            </tr>
          </thead>

          <tbody>
            {encounters.map((encounter: any) => (
              <Fragment key={encounter.id}>
                <tr>
                  <td>{encounter.date}</td>
                  <td>{encounter.visitCode}</td>
                  <td>{encounter.provider}</td>
                  <td className="view-more">
                    <div className="view-patient-container">
                      <p>View Details</p>
                      <Link to={`/patients/${patient.id}/encounters/${encounter.id}`} className="link">
                        <Icon>north_east</Icon>
                      </Link>
                    </div>
                  </td>
                </tr>
              </Fragment>
            ))}
          </tbody>
        </table>
      </div>

      <CreateEncounterModal
        open={openCreateModal}
        onClose={closeCreateModal}
        setEncounters={setEncounters}
        setOpen={setOpenCreateModal}
      />
    </div>
  );
};

export default EncountersTable;
