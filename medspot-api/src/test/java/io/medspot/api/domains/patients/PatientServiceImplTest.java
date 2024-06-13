package io.medspot.api.domains.patients;

import static org.mockito.Mockito.when;

import io.medspot.api.domains.encounters.Encounter;
import io.medspot.api.domains.encounters.EncounterRepository;
import io.medspot.api.exceptions.BadRequest;
import io.medspot.api.exceptions.Conflict;
import io.medspot.api.exceptions.ResourceNotFound;
import io.medspot.api.exceptions.ServiceUnavailable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.transaction.CannotCreateTransactionException;

public class PatientServiceImplTest {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @InjectMocks
  PatientServiceImpl patientServiceImpl;

  @Mock
  EncounterRepository encounterRepository;

  @Mock
  PatientRepository patientRepository;

  @Mock
  Patient testPatient;

  @Mock
  Encounter encounter;

  public Patient generateValidPatient() {
    return new Patient(1L, "Luz", "Lopez",
        "123-13-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    testPatient = generateValidPatient();

    when(patientRepository.save(testPatient)).thenReturn(testPatient);
  }

  @Test
  public void returns503GivenServerErrorTestingGetPatients() {
    when(patientRepository.findAll()).thenThrow(
        new CannotCreateTransactionException("message")
    );
    expectedException.expect(ServiceUnavailable.class);

    patientServiceImpl.getPatients();
  }

  @Test
  public void returns503GivenServerErrorTestingGetPatientsById() {
    when(patientRepository.findById(1L)).thenThrow(
        new CannotCreateTransactionException("message")
    );
    expectedException.expect(ServiceUnavailable.class);

    patientServiceImpl.getPatientById(1L);
  }

  @Test
  public void returnsCorrectMessageGetPatientsByNonExistentId() {
    testPatient = patientRepository.findById(20L).orElse(null);
    expectedException.expect(ResourceNotFound.class);

    patientServiceImpl.getPatientById(20L);
  }


  @Test
  public void returnsCorrectMessageIfFieldsAreNull() {
    testPatient.setFirstName(null);
    testPatient.setLastName(null);
    testPatient.setSsn(null);
    testPatient.setEmail(null);
    testPatient.setAge(null);
    testPatient.setHeight(null);
    testPatient.setWeight(null);
    testPatient.setInsurance(null);
    testPatient.setGender("");
    testPatient.setStreet("");
    testPatient.setCity("");
    testPatient.setState("");
    testPatient.setPostal("");
    expectedException.expect(BadRequest.class);
    expectedException.expectMessage(
        "First Name is required. Last Name is required. SSN is required. Email is required."
            + " Age is required. Height is required. Weight is required. Insurance is required. Gender is required."
            + " Street is required. City is required. State is required. Postal is required. ");

    patientServiceImpl.createPatient(testPatient);
  }

  @Test
  public void returnsCorrectMessageIfEmailExists() {
    testPatient.setEmail("kiwi@gmail.com");

    when(patientRepository.findByEmail(testPatient.getEmail())).thenReturn(testPatient).thenThrow(
        new Conflict("Patient with email kiwi@gmail.com already exists.")
    );

    expectedException.expect(Conflict.class);
    expectedException.expectMessage(
        "Patient with email " + testPatient.getEmail() + " already exists.");
    patientServiceImpl.createPatient(testPatient);
  }

  @Test
  public void returns503WhenServerIsDownTestingCreateMethod() {
    testPatient.setEmail("kiwi@gmail.com");

    when(patientRepository.findByEmail(testPatient.getEmail())).thenThrow(
        new DataAccessResourceFailureException("message")
    );
    expectedException.expect(ServiceUnavailable.class);

    patientServiceImpl.createPatient(testPatient);
  }

  @Test
  public void returnsCorrectErrorMessageIfUpdatePatientIdIsNull() {
    Patient update = new Patient(null, "Luz", "Lopez",
        "123-13-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");

    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));
    expectedException.expect(BadRequest.class);
    patientServiceImpl.updatePatient(testPatient.getId(), update);
  }

  @Test
  public void returnsCorrectErrorMessageIfUpdatePatientIdDoesntExist() {
    Patient update = new Patient(3345L, "Luz", "Lopez",
        "123-13-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");
    expectedException.expect(ResourceNotFound.class);
    patientServiceImpl.updatePatient(345L, update);
  }

  @Test
  public void returnsCorrectErrorMessageIfUpdateEmailExists() {
    Patient update = new Patient(null, "Luz", "Lopez",
        "123-13-1234", "lmarcano@catalyte.io",
        21L, 60L, 105L, "Medicare",
        "Female", "123 St", "Orange", "NJ", "12345");
    testPatient.setEmail("lmarcano@catalyte.io");
    when(patientRepository.findById(1L)).thenReturn(Optional.ofNullable(testPatient));

    when(patientRepository.findByEmail(testPatient.getEmail())).thenReturn(testPatient).thenThrow(
        new Conflict("Patient with email lmarcano@catalyte.io already exists.")
    );
    expectedException.expect(Conflict.class);
    patientServiceImpl.updatePatient(testPatient.getId(), update);
  }

  @Test
  public void returns503GivenServerErrorTestingUpdate() {
    testPatient.setId(1L);

    when(patientRepository.findById(1L)).thenThrow(
        new CannotCreateTransactionException("message") {

        }
    );
    expectedException.expect(ServiceUnavailable.class);

    patientServiceImpl.updatePatient(1L, testPatient);
  }

  @Test
  public void returnsCorrectMessageGivenNonexistentIdTestingDelete() {
    expectedException.expect(ResourceNotFound.class);
    patientServiceImpl.deletePatient(123L);
  }

  @Test
  public void returnsCorrectMessageGivenExistingEncounterTestingDelete() {
    encounter = new Encounter(1L, "Notes", "H7J 8W2",
        "Ames Hospital", "123.456.789-22", "Z10",
        BigDecimal.valueOf(120.35), BigDecimal.valueOf(50.00), "Broken leg",
        null, null, null, "2022-10-23");
    List<Encounter> list = new ArrayList<>();
    list.add(encounter);
    when(encounterRepository.findAll()).thenReturn(list).thenThrow(
        new Conflict("message")
    );
    expectedException.expect(Conflict.class);
    patientServiceImpl.deletePatient(1L);
  }

  @Test
  public void returns503GivenServerErrorTestingDelete() {
    testPatient.setId(1L);

    when(encounterRepository.findAll()).thenThrow(
        new CannotCreateTransactionException("message") {

        }
    );
    expectedException.expect(ServiceUnavailable.class);

    patientServiceImpl.deletePatient(1L);
  }

}
