package io.medspot.api.domains.encounters;


import io.medspot.api.domains.patients.Patient;
import io.medspot.api.domains.patients.PatientRepository;
import io.medspot.api.exceptions.BadRequest;
import io.medspot.api.exceptions.Conflict;
import io.medspot.api.exceptions.ResourceNotFound;
import io.medspot.api.exceptions.ServiceUnavailable;
import io.medspot.api.validation.EncounterValidation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

/**
 * Implements encounter service interface
 */
@Service
public class EncounterServiceImpl implements EncounterService {

  private final Logger logger = LogManager.getLogger(EncounterServiceImpl.class);

  private final EncounterValidation encounterValidation = new EncounterValidation();

  EncounterRepository encounterRepository;
  PatientRepository patientRepository;

  @Autowired
  public EncounterServiceImpl(EncounterRepository encounterRepository,
      PatientRepository patientRepository) {
    this.encounterRepository = encounterRepository;
    this.patientRepository = patientRepository;
  }

  /**
   * Gets all encounters of a patient
   *
   * @param id patient ID to get encounters for
   * @return List of encounters
   */
  @Override
  public List<Encounter> getEncounters(Long id) {

    Patient existingPatient;
    List<Encounter> allEncounters;
    List<Encounter> patientEncounter = new ArrayList<>();

    try {
      existingPatient = patientRepository.findById(id).orElse(null);
      if (existingPatient == null) {
        logger.info("Patient with ID: " + id + " does not exist.");
        throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
      }
      allEncounters = encounterRepository.findAll();
      for (Encounter e : allEncounters) {
        if (e.getPatientId() == id) {
          patientEncounter.add(e);
        }
      }
      Collections.sort(patientEncounter, (d1, d2) -> (int) (d1.getId() - d2.getId()));
      return patientEncounter;
    } catch (CannotCreateTransactionException e) {
      throw new ServiceUnavailable("Unable to connect to the server.");
    }
  }

  /**
   * Gets encounter by id
   *
   * @param id          Patient ID to get encounter for
   * @param encounterId encounter
   * @return encounter
   */
  @Override
  public Encounter getEncounterByID(Long id, Long encounterId) {
    Patient existingPatient;
    Encounter encounter;

    try {
      existingPatient = patientRepository.findById(id).orElse(null);
      if (existingPatient == null) {
        logger.info("Patient with ID: " + id + " does not exist.");
        throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
      }
      encounter = encounterRepository.findById(encounterId).orElse(null);

    } catch (CannotCreateTransactionException e) {
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (encounter != null) {
      if (existingPatient.getId() != encounter.getPatientId()) {
        throw new Conflict(
            "The encounter you are trying to get is not associated with the indicated patient."
                + " Please change patient ID to " + encounter.getPatientId() + ".");
      }
      return encounter;
    } else {
      logger.info("Encounter with ID: " + encounterId + " does not exist.");
      throw new ResourceNotFound("Encounter with ID: " + encounterId + " does not exist.");
    }

  }

  /**
   * Creates an encounter for a patient
   *
   * @param id           Patient ID to create an encounter for
   * @param newEncounter new encounter
   * @return encounter
   */
  @Override
  public Encounter createEncounter(Long id, Encounter newEncounter) {
    String errorBuilder = encounterValidation.validateEncounter(newEncounter);

    try {
      if (patientRepository.findById(id).orElse(null) == null) {
        logger.error("Patient with ID: " + id + " does not exist.");
        throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
      }
      if (newEncounter.getPatientId() != id) {
        errorBuilder += "The patientID does not match the ID of the patient you are creating an encounter for. ";
      }
    } catch (CannotCreateTransactionException e) {
      logger.error("Unable to connect to the server.");
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (errorBuilder.length() > 0) {
      throw new BadRequest(errorBuilder);
    }

    newEncounter.setTotalCost(newEncounter.getTotalCost().setScale(2));
    newEncounter.setCopay(newEncounter.getCopay().setScale(2));
    encounterRepository.save(newEncounter);
    return newEncounter;
  }

  /**
   * Updates a patients encounter
   *
   * @param id               patient ID to update an encounter for
   * @param encounterId      encounter to update
   * @param updatedEncounter updated Encounter
   * @return Encounter
   */
  @Override
  public Encounter updateEncounter(Long id, Long encounterId, Encounter updatedEncounter) {
    String errorBuilder = encounterValidation.validateEncounter(updatedEncounter);
    Encounter existingEncounter;

    try {
      existingEncounter = encounterRepository.findById(encounterId).orElse(null);
      if (updatedEncounter.getPatientId() != id) {
        errorBuilder += "The patientID does not match the ID of the patient you are updating an encounter for. ";
      }

      if (patientRepository.findById(id).orElse(null) == null) {
        logger.info("Patient with ID: " + id + " does not exist.");
        throw new ResourceNotFound("Patient with ID: " + id + " does not exist.");
      }

      if (existingEncounter == null) {
        logger.info("Encounter with ID: " + encounterId + " does not exist.");
        throw new ResourceNotFound("Encounter with ID: " + encounterId + " does not exist.");
      }
    } catch (CannotCreateTransactionException e) {
      logger.error("Unable to connect to the server.");
      throw new ServiceUnavailable("Unable to connect to the server.");
    }

    if (errorBuilder.length() > 0) {
      throw new BadRequest(errorBuilder);
    }

    if (existingEncounter.getPatientId() != updatedEncounter.getPatientId()) {
      throw new Conflict(
          "This encounter is not associated with this patient. Please change patientID to "
              + existingEncounter.getPatientId() + ". "
      );
    }

    updatedEncounter.setTotalCost(updatedEncounter.getTotalCost().setScale(2));
    updatedEncounter.setCopay(updatedEncounter.getCopay().setScale(2));
    encounterRepository.save(updatedEncounter);
    return updatedEncounter;
  }
}
