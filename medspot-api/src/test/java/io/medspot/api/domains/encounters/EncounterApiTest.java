package io.medspot.api.domains.encounters;

import static io.medspot.api.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
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
public class EncounterApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @InjectMocks
  Encounter encounter;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Before
  public void setUpPostPatient() {
    encounter.setPatientId(1L);
    encounter.setVisitCode("H7J 3D3");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("L21");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-11-10");
  }

  @Test
  public void happyPathGetPatientsEncountersReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/1/encounters"))
        .andExpect(status().isOk());
  }

  @Test
  public void sadPathGetPatientsEncountersNonExistentIdReturns404() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/10000/encounters"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void happyPathGetPatientsEncounterByIdReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/1/encounters/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void sadPathGetPatientsEncounterByNonExistentIdReturns404() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/1/encounters/1345"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void sadPathGetPatientEncounterByIdNotAssociatedWithEncounterReturns409()
      throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/2/encounters/1"))
        .andExpect(status().isConflict());
  }

  @Test
  public void happyPathPostValidEncounterReturns201() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    mockMvc.perform(
            post(PATIENTS_PATH + "/1/encounters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isCreated());
  }

  @Test
  public void sadPathPostInvalidEncounterReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setVisitCode("H7J3D3");
    encounter.setProvider("");

    mockMvc.perform(
            post(PATIENTS_PATH + "/1/encounters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void sadPathPostMisMatchPatientIdReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setPatientId(99L);

    mockMvc.perform(
            post(PATIENTS_PATH + "/1/encounters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void sadPathPostToNonExistentPatientIdReturns404() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setPatientId(99L);

    mockMvc.perform(
            post(PATIENTS_PATH + "/99/encounters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void happyPathUpdateEncounterReturns200() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(1L);
    encounter.setPatientId(1L);
    encounter.setVisitCode("H7J 9L9");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/1/encounters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isOk());
  }

  @Test
  public void sadPathUpdateEncounterNonExistentEncounterIdReturns404() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(99L);
    encounter.setPatientId(1L);
    encounter.setVisitCode("H7J 9L9");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/1/encounters/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void sadPathUpdateEncounterNonExistentPatientIdReturns404() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(1L);
    encounter.setPatientId(99L);
    encounter.setVisitCode("H7J 9L9");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/99/encounters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isNotFound());
  }

  @Test
  public void sadPathUpdateEncounterWithInvalidFieldsReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(1L);
    encounter.setPatientId(1L);
    encounter.setVisitCode("aaH7J 9L9aa");
    encounter.setProvider("");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(null);
    encounter.setCopay(null);
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/1/encounters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void sadPathUpdateEncounterWithMismatchPatientIdReturns400() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(1L);
    encounter.setPatientId(12L);
    encounter.setVisitCode("H7J 9L9");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/2/encounters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void sadPathUpdateEncounterNotAssociatedWithPatientReturns409() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    encounter.setId(1L);
    encounter.setPatientId(2L);
    encounter.setVisitCode("H7J 9L9");
    encounter.setProvider("Insurance");
    encounter.setBillingCode("123.444.584-44");
    encounter.setIcd10("M30");
    encounter.setTotalCost(BigDecimal.valueOf(23.64));
    encounter.setCopay(BigDecimal.valueOf(40));
    encounter.setChiefComplaint("Stomach pain");
    encounter.setDate("2022-01-23");

    mockMvc.perform(
            put(PATIENTS_PATH + "/2/encounters/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(encounter)))
        .andExpect(status().isConflict());
  }


}
