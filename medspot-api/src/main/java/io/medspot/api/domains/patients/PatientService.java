package io.medspot.api.domains.patients;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Patient Service
 */
public interface PatientService {

  List<Patient> getPatients();

  Patient getPatientById(Long id);

  Patient createPatient(Patient newPatient);

  Patient updatePatient(Long id, Patient updatedPatient);

  String deletePatient(Long id);

}
