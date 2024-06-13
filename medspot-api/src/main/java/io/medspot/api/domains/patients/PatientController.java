package io.medspot.api.domains.patients;


import static io.medspot.api.constants.Paths.PATIENTS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the patient entity
 */
@RestController
@RequestMapping(value = PATIENTS_PATH)
public class PatientController {

  @Autowired
  private PatientService patientService;

  @GetMapping
  public ResponseEntity<List<Patient>> getPatients() {
    return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
    return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Patient> createPatient(
      @RequestBody Patient newPatient
  ) {
    return new ResponseEntity<>(patientService.createPatient(newPatient), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Patient> updatePatient(
      @PathVariable Long id,
      @RequestBody Patient updatedPatient
  ) {
    return new ResponseEntity<>(patientService.updatePatient(id, updatedPatient), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deletePatient(@PathVariable Long id) {
    return new ResponseEntity<>(patientService.deletePatient(id), HttpStatus.NO_CONTENT);
  }

}
