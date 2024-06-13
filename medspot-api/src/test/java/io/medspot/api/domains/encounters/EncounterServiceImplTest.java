package io.medspot.api.domains.encounters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.medspot.api.domains.patients.Patient;
import io.medspot.api.domains.patients.PatientRepository;
import io.medspot.api.exceptions.BadRequest;
import io.medspot.api.exceptions.Conflict;
import io.medspot.api.exceptions.ResourceNotFound;
import io.medspot.api.exceptions.ServiceUnavailable;
import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.CannotCreateTransactionException;

public class EncounterServiceImplTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @InjectMocks
  EncounterServiceImpl encounterServiceImpl;

  @Mock
  EncounterRepository encounterRepository;

  @Mock
  PatientRepository patientRepository;

  Encounter testEncounter;
  Patient testPatient;

  public Encounter generateValidEncounter() {
    return new Encounter(1L, "Notes", "H7J 8W2",
        "Ames Hospital", "123.456.789-22", "Z10",
        BigDecimal.valueOf(120.35), BigDecimal.valueOf(50.00), "Broken leg",
        null, null, null, "2022-10-23");
  }

  public Patient generateValidPatient() {
    Patient patient = new Patient(1L, "Luz", "Lopez",
        "123-12-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");
    patientRepository.save(testPatient);
    return patient;

  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    testEncounter = generateValidEncounter();
    testPatient = generateValidPatient();

    when(encounterRepository.save(any(Encounter.class))).thenReturn(testEncounter);
  }

  @Test
  public void returnsCorrectExceptionGetEncounterByNonExistentId() {
    when(patientRepository.findById(12L)).thenThrow(
        new ResourceNotFound("message")
    );
    expectedException.expect(ResourceNotFound.class);
    encounterServiceImpl.getEncounters(testPatient.getId());
  }

  @Test
  public void returns503ServerErrorTestingGetEncounters() {
    testPatient.setId(1L);
    when(patientRepository.findById(1L)).thenThrow(
        new CannotCreateTransactionException("message")
    );
    expectedException.expect(ServiceUnavailable.class);
    encounterServiceImpl.getEncounters(testPatient.getId());
  }

  @Test
  public void returnCorrectExceptionGivenNonExistentPatientId() {
    testPatient.setId(1L);
    testEncounter.setId(1L);

    expectedException.expect(ResourceNotFound.class);
    expectedException.expectMessage("Patient with ID: " + 1L + " does not exist.");
    encounterServiceImpl.getEncounterByID(1L, 1L);
  }

  @Test
  public void return503ServerErrorTestingGetEncounterById() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    when(encounterRepository.findById(1L)).thenThrow(
        new CannotCreateTransactionException("message")
    );
    expectedException.expect(ServiceUnavailable.class);
    encounterServiceImpl.getEncounterByID(1L, 1L);
  }

  @Test
  public void returnCorrectExceptionGivenPatientIdIsNotAssociatedWithGettingEncounterById() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    when(encounterRepository.findById(1L)).thenReturn(Optional.ofNullable(testEncounter));
    testEncounter.setPatientId(2L);
    expectedException.expect(Conflict.class);
    encounterServiceImpl.getEncounterByID(1L, 1L);
  }

  @Test
  public void returnCorrectExceptionGivenNonExistentEncounterId() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));

    expectedException.expect(ResourceNotFound.class);
    encounterServiceImpl.getEncounterByID(1L, 1L);
  }

  @Test
  public void returnCorrectExceptionGivenNonExistentPatientIdTestingCreate() {
    expectedException.expect(ResourceNotFound.class);
    encounterServiceImpl.createEncounter(1L, testEncounter);
  }

  @Test
  public void returnCorrectExceptionGivenMismatchPatientIdTestingCreate() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    testEncounter.setPatientId(4L);
    expectedException.expect(BadRequest.class);
    encounterServiceImpl.createEncounter(1L, testEncounter);
  }

  @Test
  public void return503ServerErrorTestingCreate() {
    testEncounter.setPatientId(1L);
    when(patientRepository.findById(1L))
        .thenThrow(
            new CannotCreateTransactionException("message")
        );
    expectedException.expect(ServiceUnavailable.class);
    encounterServiceImpl.createEncounter(1L, testEncounter);
  }

  @Test
  public void returnCorrectExceptionGivenMismatchIdTestingUpdate() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    when(encounterRepository.findById(1L)).thenReturn(Optional.ofNullable(testEncounter));
    testEncounter.setPatientId(4L);
    expectedException.expect(BadRequest.class);
    encounterServiceImpl.updateEncounter(1L, 1L, testEncounter);
  }

  @Test
  public void returnCorrectExceptionGivenNonExistentPatientIdTestingUpdate() {
    when(encounterRepository.findById(1L)).thenReturn(Optional.ofNullable(testEncounter));
    expectedException.expect(ResourceNotFound.class);
    encounterServiceImpl.updateEncounter(1L, 1L, testEncounter);
  }

  @Test
  public void returnCorrectExceptionGivenNonExistentEncounterIdTestingUpdate() {
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    expectedException.expect(ResourceNotFound.class);
    encounterServiceImpl.updateEncounter(1L, 1L, testEncounter);

  }

  @Test
  public void return503ServerErrorTestingUpdate() {
    testEncounter.setPatientId(1L);
    when(encounterRepository.findById(1L))
        .thenThrow(
            new CannotCreateTransactionException("message")
        );
    expectedException.expect(ServiceUnavailable.class);
    encounterServiceImpl.updateEncounter(1L, 1L, testEncounter);
  }

  @Test
  public void returnCorrectExceptionPatientNotAssociatedEncounterTestingUpdate() {
    Encounter updated = new Encounter(2L, "Notes", "H7J 8W2",
        "Ames Hospital", "123.456.789-22", "Z10",
        BigDecimal.valueOf(120.35), BigDecimal.valueOf(50.00), "Broken leg",
        null, null, null, "2022-10-23");
    Patient patient = new Patient(2L, "Luz", "Lopez",
        "123-13-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    when(patientRepository.findById(2L)).thenReturn(Optional.ofNullable(patient));
    when(encounterRepository.findById(1L)).thenReturn(Optional.ofNullable(testEncounter));
    expectedException.expect(Conflict.class);
    encounterServiceImpl.updateEncounter(2L, 1L, updated);
  }

}
