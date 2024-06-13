package io.medspot.api.validation;

import io.medspot.api.domains.patients.Patient;
import io.medspot.api.domains.patients.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides validation methods for the fields of Patient objects
 */
public class PatientValidation {

  RegexMatcher regexMatcher = new RegexMatcher();

  @Autowired
  private PatientRepository patientRepository;

  /**
   * Validates Patient
   *
   * @param patient Patient
   * @return error message
   */
  public String validatePatient(Patient patient) {
    String errorBuilder = "";

    errorBuilder += validateRequiredFields(patient.getFirstName(), patient.getLastName(),
        patient.getSsn(), patient.getEmail(), patient.getAge(),
        patient.getHeight(), patient.getWeight(), patient.getInsurance(), patient.getGender(),
        patient.getStreet(), patient.getCity(), patient.getState(), patient.getPostal());
    errorBuilder += validateName(patient.getFirstName(), patient.getLastName());
    errorBuilder += validateSsn(patient.getSsn());
    errorBuilder += validateEmail(patient.getEmail());
    errorBuilder += validateNumberFields(patient.getAge(), patient.getHeight(),
        patient.getWeight());
    errorBuilder += validateGender(patient.getGender());
    errorBuilder += validateState(patient.getState());
    errorBuilder += validatePostal(patient.getPostal());

    return errorBuilder;
  }

  /**
   * Validates fields are filled in
   *
   * @param firstName firstName
   * @param lastName  lastName
   * @param ssn       ssn
   * @param email     email
   * @param age       age
   * @param height    height
   * @param weight    weight
   * @param insurance insurance
   * @param gender    gender
   * @param street    street
   * @param city      city
   * @param state     state
   * @param postal    postal
   * @return error message
   */
  public String validateRequiredFields(String firstName, String lastName, String ssn, String email,
      Long age, Long height, Long weight, String insurance,
      String gender, String street, String city, String state, String postal) {
    String errors = "";
    String regex = "^\\s+$";

    if (firstName == null || firstName == "" || regexMatcher.exactMatch(firstName, regex)) {
      errors += "First Name is required. ";
    }
    if (lastName == null || lastName == "" || regexMatcher.exactMatch(lastName, regex)) {
      errors += "Last Name is required. ";
    }
    if (ssn == null || ssn == "" || regexMatcher.exactMatch(ssn, regex)) {
      errors += "SSN is required. ";
    }
    if (email == null || email == "" || regexMatcher.exactMatch(email, regex)) {
      errors += "Email is required. ";
    }
    if (age == null) {
      errors += "Age is required. ";
    }
    if (height == null) {
      errors += "Height is required. ";
    }
    if (weight == null) {
      errors += "Weight is required. ";
    }

    if (insurance == null || insurance == "" || regexMatcher.exactMatch(insurance, regex)) {
      errors += "Insurance is required. ";
    }
    if (gender == null || gender == "" || regexMatcher.exactMatch(gender, regex)) {
      errors += "Gender is required. ";
    }
    if (street == null || street == "" || regexMatcher.exactMatch(street, regex)) {
      errors += "Street is required. ";
    }

    if (city == null || city == "" || regexMatcher.exactMatch(city, regex)) {
      errors += "City is required. ";
    }
    if (state == null || state == "" || regexMatcher.exactMatch(state, regex)) {
      errors += "State is required. ";
    }
    if (postal == null || postal == "" || regexMatcher.exactMatch(postal, regex)) {
      errors += "Postal is required. ";
    }

    return errors;
  }

  /**
   * Validates first and last name are valid format
   *
   * @param firstName
   * @param lastName
   * @return
   */
  public String validateName(String firstName, String lastName) {
    String errors = "";
    String regex = "^[A-Za-z-']*$";

    if (firstName != null && !regexMatcher.exactMatch(firstName, regex)) {
      errors += "First Name must be alphabetic and can consist of hyphens and apostrophe's. ";
    }

    if (lastName != null && !regexMatcher.exactMatch(lastName, regex)) {
      errors += "Last Name must be alphabetic and can consist of hyphens and apostrophe's. ";
    }

    return errors;
  }

  /**
   * Validates SSN is in correct format DDD-DD-DDDD
   *
   * @param ssn ssn
   * @return error string
   */
  public String validateSsn(String ssn) {
    String errors = "";
    String regex = "^([0-9]){3}-([0-9]){2}-([0-9]){4}$";

    if (ssn != null && !regexMatcher.exactMatch(ssn, regex)) {
      errors += "SSN must match the format DDD-DD-DDDD where D is any numerical digit. ";
    }

    return errors;
  }

  /**
   * Validates email is in correct format, A@L.L
   *
   * @param email email
   * @return error string
   */
  public String validateEmail(String email) {
    String errors = "";
    String regex = "^[a-zA-Z0-9]+@[a-zA-Z]+?\\.[a-zA-Z]+$";

    if (email != null && !regexMatcher.exactMatch(email, regex)) {
      errors += "Email must follow the format A@L.L where A is any alphanumeric character and L is any alphabetic character. ";
    }

    return errors;
  }

  /**
   * Validates number fields are greater than 0
   *
   * @param age    age
   * @param height height
   * @param weight weight
   * @return error string
   */
  public String validateNumberFields(Long age, Long height, Long weight) {
    String errors = "";

    if (age != null && age < 0) {
      errors += "Age must be a valid whole number. ";
    }
    if (height != null && height < 0) {
      errors += "Height must be a valid whole number. ";
    }
    if (weight != null && weight < 0) {
      errors += "Weight must be a valid whole number. ";
    }

    return errors;
  }

  /**
   * Validates gender is Female, Male, or Other
   *
   * @param gender gender
   * @return error string
   */
  public String validateGender(String gender) {
    String errors = "";

    if (gender != null && !gender.equals("Female")
        && !gender.equals("Male")
        && !gender.equals("Other")) {
      errors += "Gender must be Male, Female, or Other. ";
    }

    return errors;
  }

  /**
   * Validates State is a valid US state in the format of the states abbreviation
   *
   * @param state state
   * @return error string
   */
  public String validateState(String state) {
    String errors = "";
    List<String> states = new ArrayList<>();
    states.add("AL");
    states.add("AK");
    states.add("AZ");
    states.add("AR");
    states.add("CA");
    states.add("CO");
    states.add("CT");
    states.add("DE");
    states.add("DC");
    states.add("FL");
    states.add("GA");
    states.add("HI");
    states.add("ID");
    states.add("IL");
    states.add("IN");
    states.add("IA");
    states.add("KS");
    states.add("KY");
    states.add("LA");
    states.add("ME");
    states.add("MD");
    states.add("MA");
    states.add("MI");
    states.add("MN");
    states.add("MS");
    states.add("MO");
    states.add("MT");
    states.add("NE");
    states.add("NV");
    states.add("NH");
    states.add("NJ");
    states.add("NM");
    states.add("NY");
    states.add("NC");
    states.add("ND");
    states.add("OH");
    states.add("OK");
    states.add("OR");
    states.add("PA");
    states.add("RI");
    states.add("SC");
    states.add("SD");
    states.add("TN");
    states.add("TX");
    states.add("UT");
    states.add("VT");
    states.add("VA");
    states.add("WA");
    states.add("WV");
    states.add("WI");
    states.add("WY");

    if (state != null && !states.contains(state)) {
      errors += "State must be a valid US state in the format LL where L is any capital letter. ";
    }

    return errors;
  }

  /**
   * Validates postal is in valid format DDDDD or DDDDD-DDDD
   *
   * @param postal postal
   * @return error string
   */
  public String validatePostal(String postal) {
    String errors = "";
    String regex = "^[0-9]{5}(?:-[0-9]{4})?$";

    if (postal != null && !regexMatcher.exactMatch(postal, regex)) {
      errors += "Postal must be in the format DDDDD or DDDDD-DDDD where D is any numerical digit. ";
    }

    return errors;
  }
}
