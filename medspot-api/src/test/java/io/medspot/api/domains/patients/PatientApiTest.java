package io.medspot.api.domains.patients;

import static io.medspot.api.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @InjectMocks
  Patient patient;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Before
  public void setUpPostPatient() {
    patient.setFirstName("Luz");
    patient.setLastName("Lopez");
    patient.setSsn("123-12-1222");
    patient.setEmail("email@email.com");
    patient.setAge(21L);
    patient.setHeight(60L);
    patient.setWeight(120L);
    patient.setInsurance("Insurance");
    patient.setGender("Female");
    patient.setStreet("123 Orange St");
    patient.setCity("Mango");
    patient.setState("NJ");
    patient.setPostal("12345");
  }

  @Test
  public void happyPathGetAllPatientsReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void happyPathGetPatientByIdReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void sadPathGetPatientByNonExistentIdReturns404() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/99"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void happyPathPostValidPatientReturns201() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(
            post(PATIENTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isCreated());
  }

  @Test
  public void sadPathPostPatientWithExistingEmailReturns409() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setEmail("lmarcano@catalyte.io");
    mockMvc.perform(
            post(PATIENTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isConflict());
  }

  @Test
  public void sadPathPostInvalidPatientReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setEmail("");
    patient.setGender(null);
    mockMvc.perform(
            post(PATIENTS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void happyPathUpdateValidPatientReturns200() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setId(2L);
    patient.setFirstName("Luz");
    patient.setLastName("Marcano");
    patient.setSsn("123-44-1222");
    patient.setEmail("updating@gmail.com");
    patient.setAge(21L);
    patient.setHeight(60L);
    patient.setWeight(120L);
    patient.setInsurance("Insurance");
    patient.setGender("Other");
    patient.setStreet("123 Orange St");
    patient.setCity("Mango");
    patient.setState("MD");
    patient.setPostal("12345-9003");
    mockMvc.perform(
            put(PATIENTS_PATH + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isOk());
  }

  @Test
  public void sadPathUpdatePatientWithNonExistentIdReturns404() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setId(22L);
    patient.setFirstName("Luz");
    patient.setLastName("Marcano");
    patient.setSsn("123-44-1222");
    patient.setEmail("updating@gmail.com");
    patient.setAge(21L);
    patient.setHeight(60L);
    patient.setWeight(120L);
    patient.setInsurance("Insurance");
    patient.setGender("Other");
    patient.setStreet("123 Orange St");
    patient.setCity("Mango");
    patient.setState("MD");
    patient.setPostal("12345-9003");
    mockMvc.perform(
            put(PATIENTS_PATH + "/22")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void sadPathUpdatePatientWithExistingEmailReturns409() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setId(2L);
    patient.setFirstName("Luz");
    patient.setLastName("Marcano");
    patient.setSsn("123-44-1222");
    patient.setEmail("lmarcano@catalyte.io");
    patient.setAge(21L);
    patient.setHeight(60L);
    patient.setWeight(120L);
    patient.setInsurance("Insurance");
    patient.setGender("Other");
    patient.setStreet("123 Orange St");
    patient.setCity("Mango");
    patient.setState("MD");
    patient.setPostal("12345-9003");
    mockMvc.perform(
            put(PATIENTS_PATH + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isConflict());
  }

  @Test
  public void sadPathUpdatePatientWithInvalidFieldsReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    patient.setId(2L);
    patient.setEmail("");
    patient.setFirstName("");
    patient.setLastName("");
    patient.setSsn("a123-44-1222a");

    mockMvc.perform(
            put(PATIENTS_PATH + "/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void happyPathDeletePatientWithNoEncountersReturns204() throws Exception {

    mockMvc.perform(
        delete(PATIENTS_PATH + "/3")).andExpect(status().isNoContent());
  }

  @Test
  public void sadPathDeletePatientWithExistingEncounterReturns409() throws Exception {
    mockMvc.perform(
        delete(PATIENTS_PATH + "/2")).andExpect(status().isConflict());
  }

  @Test
  public void sadPathDeletePatientWithNonExistentIdReturns404() throws Exception {
    mockMvc.perform(
        delete(PATIENTS_PATH + "/25")).andExpect(status().isNotFound());
  }


}
