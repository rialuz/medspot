import { Box, Icon, Modal } from "@mui/material";
import { deletePatient } from "../../helpers/apiHelper";
import { useNavigate, useParams } from "react-router-dom";
import { useState } from "react";

const style = {
    position: "absolute",
    top: "50%",
    left: "50%",
    minHeight: "50px",
    transform: "translate(-50%, -50%)",
    width: 350,
    bgcolor: "background.paper",
    border: "2px solid #000",
    boxShadow: 24,
    p: 4,
  };

function DeletePatientModal({id, open, onClose, setOpen}){
    const [errorMessage, setErrorMessage] = useState(false)

    const navigate = useNavigate();

    const navigateAfterDelete = () => {
        navigate('/');
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
                        <h3 className="form-title">Are you sure you want to delete this patient?</h3> 
                        { errorMessage ? 
                        (
                            <span className="error">Unable to delete patient. The patient you are trying to delete currently has encounters.</span>
                        ) 
                        : 
                        (
                            <></>
                        )
                         }
                        <div className="patient-btn">
                       <button onClick={() => deletePatient(id, navigateAfterDelete, setErrorMessage)} >Delete Patient</button>                            
                        </div>   

                </Box>

            </Modal>
        </div>
    )
}


export default DeletePatientModal;