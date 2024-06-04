// Store all SQL queries

const getPatients = "SELECT * FROM patients";

/**
 * $1 - indicates parameters 
 */
const getPatientById = "SELECT * FROM patients WHERE id = $1"

const checkEmailExists = "SELECT p FROM patients p WHERE p.email = $1"

const addPatient = "INSERT INTO patients (firstname, lastname, ssn, email, age, dob, height, weight, insurance, gender, street, city, state, postal) VALUES ($1, $2,$3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14)";

const deletePatient = "DELETE FROM patients WHERE id = $1"

const updatePatient = "UPDATE patients SET firstname = $1, lastname = $2, ssn = $3, email = $4, age = $5, dob = $6, height = $7, weight = $8, insurance = $9, gender = $10, street = $11, city = $12, state = $13, postal = $14 WHERE id = $15"


module.exports = {
    getPatients,
    getPatientById,
    checkEmailExists,
    addPatient,
    deletePatient,
    updatePatient
}