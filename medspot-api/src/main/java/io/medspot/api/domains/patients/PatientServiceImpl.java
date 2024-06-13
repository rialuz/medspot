package io.medspot.api.domains.patients;


import io.medspot.api.domains.encounters.Encounter;
import io.medspot.api.domains.encounters.EncounterRepository;
import io.medspot.api.exceptions.BadRequest;
import io.medspot.api.exceptions.Conflict;
import io.medspot.api.exceptions.ResourceNotFound;
import io.medspot.api.exceptions.ServiceUnavailable;
import io.medspot.api.validation.PatientValidation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;


/**
 * Implements patient service interface
 */
@Service
public class PatientServiceImpl implements PatientService {

  private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

  private final PatientValidation patientValidation = new PatientValidation();

  EncounterRepository encounterRepository;
  PatientRepository patientRepository;

  @Autowired
  public PatientServiceImpl(EncounterRepository encounterRepository,
      PatientRepository patientRepository) {
    this.encounterRepository = encounterRepository;
    this.patientRepository = patientRepository;
  }

  /**
   * Gets list of all patients
   *
   * @return List of all patients
   */
  @Override
  public List<Patient> getPatients() {
    List<Patient> patients;
    try {
      patients = patientRepository.findAll();
      Collections.sort(patients, (d1, d2) -> (int) (d1.getId() - d2.getId()
      ));
      return patients;
    } catch (CannotCreateTransactionException e) {
      throw new ServiceUnavailable("Unable to connect to the server.");
    }
  }

  /**
   * Gets patient by indicated ID
   *
   * @param id ID of patient
   * @return patient
   */
  @Override
  public Patient getPatientById(Long id) {
    Patient patient;

    try {
      patient = patientRepository.findById(id).orElse(null);
    } catch (CannotCreateTransactionException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (patient != null) {
      return patient;
    } else {
      logger.info("Patient with ID: " + id + " does not exist.");
      throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
    }

  }

  /**
   * Creates a new patient
   *
   * @param newPatient new Patient
   * @return newly created patient
   */
  @Override
  public Patient createPatient(Patient newPatient) {
    String errorBuilder = patientValidation.validatePatient(newPatient);
    Patient existingPatient;

    try {
      existingPatient = patientRepository.findByEmail(newPatient.getEmail());
    } catch (DataAccessResourceFailureException e) {
      logger.error("Unable to connect to the server.");
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (existingPatient != null) {
      logger.error("Patient with email " + existingPatient.getEmail() + " already exists.");
      throw new Conflict("Patient with email " + existingPatient.getEmail() + " already exists.");
    }

    if (errorBuilder.length() > 0) {
      throw new BadRequest(errorBuilder);
    }

    patientRepository.save(newPatient);
    return newPatient;
  }

  /**
   * Updates existing patient
   *
   * @param id             ID of patient to update
   * @param updatedPatient updated Patient
   * @return updated Patient
   */
  @Override
  public Patient updatePatient(Long id, Patient updatedPatient) {
    String errorBuilder = patientValidation.validatePatient(updatedPatient);
    Patient existingPatient;

    if (updatedPatient.getId() == null || updatedPatient.getId() != id) {
      errorBuilder += "The ID given in the URL does not match the ID of the patient you are trying to update. ";
    }

    try {
      if (patientRepository.findById(id).orElse(null) == null) {
        throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
      }
      existingPatient = patientRepository.findByEmail(updatedPatient.getEmail());
    } catch (CannotCreateTransactionException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (existingPatient != null && (existingPatient.getId() != updatedPatient.getId())) {
      logger.error("Patient with the email " + updatedPatient.getEmail() + " already exists. ");
      throw new Conflict(
          "Patient with the email " + updatedPatient.getEmail() + " already exists. ");
    }

    if (errorBuilder.length() > 0) {
      throw new BadRequest(errorBuilder);
    }
    patientRepository.save(updatedPatient);
    return updatedPatient;
  }

  /**
   * Deletes patient
   *
   * @param id ID of patient to delete
   * @return Successful deletion message
   */
  @Override
  public String deletePatient(Long id) {
    List<Encounter> allEncounters;
    List<Encounter> patientEncounter = new ArrayList<>();

    try {
      allEncounters = encounterRepository.findAll();
      for (Encounter e : allEncounters) {
        if (e.getPatientId() == id) {
          patientEncounter.add(e);
        }
      }
      if (patientEncounter.size() > 0) {
        logger.error(
            "The patient you are trying to delete currently has encounters. You are only able to delete a patient with no encounters.");
        throw new Conflict(
            "The patient you are trying to delete currently has encounters. You are only able to delete a patient with no encounters.");
      }
    } catch (CannotCreateTransactionException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (patientRepository.findById(id).orElse(null) == null) {
      throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
    }

    patientRepository.deleteById(id);
    return "Patient was successfully deleted.";
  }
}
