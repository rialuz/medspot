const pool = require('../../db');
const query = require('./queries');

const getPatients = (req, res) => {
    console.log('getting patients')
    pool.query(query.getPatients, (err, results) => {
        if(err) throw err;
        res.status(200).json(results.rows);
    });
}

const getPatientById = (req, res) => {
    const id = parseInt(req.params.id);
    console.log(`getting student with id: ${id}`)
    pool.query(query.getPatientById, [id], (err, results) => {
        if(err) throw err;
        res.status(200).json(results.rows);
    })
}

const addPatient = (req, res) => {
    const { firstname,lastname,ssn,email,age,dob,height,weight,insurance,gender,street,city,state,postal } = req.body;

    // check if email already exists
    pool.query(query.checkEmailExists, [email], (err, results) => {
        if(results.rows.length) {
            res.send("Email already exists.");
        }
        // add student to db
        pool.query(query.addPatient, [firstname,lastname,ssn,email,age,dob,height,weight,insurance,gender,street,city,state,postal], (err, results) => {
            if(err) throw err;
            res.status(201).send("Patient Created Successfully");
        })

    })
}

const updatePatient = (req, res) => {
    const id = parseInt(req.params.id); 
    const { firstname,lastname,ssn,email,age,dob,height,weight,insurance,gender,street,city,state,postal } = req.body;

    pool.query(query.getPatientById, [id], (err, results) => {
        const noPatientFound = !results.rows.length;
        if(noPatientFound) res.send("Patient does not exist in the database.");
        
        pool.query(query.updatePatient, [firstname,lastname,ssn,email,age,dob,height,weight,insurance,gender,street,city,state,postal, id], (err, results) => {
            if(err) throw err;
            res.status(200).send("Student updated successfully");
        })
    })
}

const deletePatient = (req, res) => {
    const id = parseInt(req.params.id);
    pool.query(query.getPatientById, [id], (err, results) => {
        const noPatientFound = !results.rows.length;
        if(noPatientFound) res.send("Patient does not exist in the database.");
        
        pool.query(query.deletePatient, [id], (err, results) => {
            if(err) throw err;
            res.status(200).send("Patient removed successfully.")
        })
    })
}

module.exports = {
    getPatients,
    getPatientById,
    addPatient,
     deletePatient,
     updatePatient
}