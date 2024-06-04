const { Router } = require('express');
const controller = require('./controller');

const router = Router();

//should send back patient data
/**
 * Query db
 * Get JSON response of patients
 * Send back to user
 */
router.get('/', controller.getPatients);
router.get('/:id', controller.getPatientById);
router.post('/', controller.addPatient);
router.put('/:id', controller.updatePatient);
router.delete('/:id', controller.deletePatient);



module.exports = router;