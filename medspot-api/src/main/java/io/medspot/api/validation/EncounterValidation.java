package io.medspot.api.validation;


import io.medspot.api.domains.encounters.Encounter;
import io.medspot.api.domains.encounters.EncounterRepository;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Provides validation methods for the fields of Encounter objects
 */
public class EncounterValidation {

  RegexMatcher regexMatcher = new RegexMatcher();

  @Autowired
  private EncounterRepository encounterRepository;

  /**
   * Validates Encounter
   *
   * @param encounter Encounter
   * @return error message
   */
  public String validateEncounter(Encounter encounter) {
    String errorBuilder = "";

    errorBuilder += validateRequiredFields(encounter.getPatientId(), encounter.getVisitCode(),
        encounter.getProvider(),
        encounter.getBillingCode(), encounter.getIcd10(), encounter.getTotalCost(),
        encounter.getCopay(), encounter.getChiefComplaint(), encounter.getDate());

    errorBuilder += validateTotalCostAndCopay(encounter.getTotalCost(), encounter.getCopay());
    errorBuilder += validateVisitCode(encounter.getVisitCode());
    errorBuilder += validateBillingCode(encounter.getBillingCode());
    errorBuilder += validateICD10(encounter.getIcd10());
    errorBuilder += validateNumberFields(encounter.getPulse(), encounter.getSystolic(),
        encounter.getDiastolic());
    errorBuilder += validateDate(encounter.getDate());

    return errorBuilder;
  }

  /**
   * Validates fields are filled in
   *
   * @param patientId      patientID
   * @param visitCode      visitCode
   * @param provider       provider
   * @param billingCode    billingCode
   * @param icd10          idc10
   * @param totalCost      totalCost
   * @param copay          copay
   * @param chiefComplaint chiefComplaint
   * @param date           date
   * @return error string
   */
  public String validateRequiredFields(Long patientId, String visitCode, String provider,
      String billingCode,
      String icd10, BigDecimal totalCost, BigDecimal copay, String chiefComplaint, String date) {
    String errors = "";
    String regex = "^\\s+$";

    if (patientId == null) {
      errors += "Patient ID is required. ";
    }
    if (visitCode == null || visitCode == "" || regexMatcher.containsMatch(visitCode, regex)) {
      errors += "Visit code is required. ";
    }
    if (provider == null || provider == "" || regexMatcher.containsMatch(provider, regex)) {
      errors += "Provider is required. ";
    }
    if (billingCode == null || billingCode == "" || regexMatcher.containsMatch(billingCode,
        regex)) {
      errors += "Billing code is required. ";
    }
    if (icd10 == null || icd10 == "" || regexMatcher.containsMatch(icd10, regex)) {
      errors += "ICD10 is required. ";
    }
    if (totalCost == null) {
      errors += "Total cost is required. ";
    }
    if (copay == null) {
      errors += "Copay is required. ";
    }
    if (chiefComplaint == null || chiefComplaint == "" || regexMatcher.containsMatch(chiefComplaint,
        regex)) {
      errors += "Chief complaint is required. ";
    }
    if (date == null || date == "" || regexMatcher.containsMatch(date, regex)) {
      errors += "Date is required. ";
    }

    return errors;
  }

  /**
   * Validates total cost and copay are greater than 0.
   *
   * @param totalCost total cost
   * @param copay     copay
   * @return error string
   */
  public String validateTotalCostAndCopay(BigDecimal totalCost, BigDecimal copay) {
    String errors = "";

    if (totalCost != null && totalCost.compareTo(BigDecimal.valueOf(0)) < 0) {
      errors += "Total cost must not be negative. ";
    }

    if (copay != null && copay.compareTo(BigDecimal.valueOf(0)) < 0) {
      errors += "Copay must not be negative. ";
    }

    return errors;
  }

  /**
   * Validates visit code is in correct format LDL DLD
   *
   * @param visitCode visit code
   * @return error string
   */
  public String validateVisitCode(String visitCode) {
    String errors = "";
    String regex = "^([A-Z]){1}([0-9]){1}([A-Z]){1} ([0-9]){1}([A-Z]){1}([0-9]){1}$";

    if (visitCode != null && !regexMatcher.containsMatch(visitCode, regex)) {
      errors += "Visit code must match the format LDL DLD where L is any capital alphabetical character and D is any numerical digit. ";
    }

    return errors;
  }

  /**
   * Validates billing code is in correct format DDD.DDD.DDD-DD
   *
   * @param billingCode billing code
   * @return error string
   */
  public String validateBillingCode(String billingCode) {
    String errors = "";
    String regex = "^([0-9]){3}.([0-9]){3}.([0-9]){3}-([0-9]){2}$";

    if (billingCode != null && !regexMatcher.containsMatch(billingCode, regex)) {
      errors += "Billing code must match the format DDD.DDD.DDD-DD where D is any numerical digit. ";
    }

    return errors;
  }

  /**
   * Validates icd10 is in correct format LDD
   *
   * @param icd10 icd10
   * @return error string
   */
  public String validateICD10(String icd10) {
    String errors = "";
    String regex = "^([A-Z]){1}([0-9]){2}$";

    if (icd10 != null && !regexMatcher.containsMatch(icd10, regex)) {
      errors += "ICD10 must match the format LDD where L is any capital letter and D is any numerical digit. ";
    }

    return errors;
  }

  /**
   * Validates pulse, systolic, and diastolic are valid whole numbers
   *
   * @param pulse     pulse
   * @param systolic  systolic
   * @param diastolic diastolic
   * @return error string
   */
  public String validateNumberFields(Long pulse, Long systolic, Long diastolic) {
    String errors = "";

    if (pulse != null && pulse < 0) {
      errors += "Pulse must be a valid whole number. ";
    }
    if (systolic != null && systolic < 0) {
      errors += "Systolic must be a valid whole number. ";
    }
    if (diastolic != null && diastolic < 0) {
      errors += "Diastolic must be a valid whole number. ";
    }

    return errors;
  }

  /**
   * Validates date is in correct format YYYY-MM-DD
   *
   * @param date
   * @return
   */
  public String validateDate(String date) {
    String errors = "";
    String regex = "^\\d{4}-\\d{2}-\\d{2}$";

    if (date != null && !regexMatcher.containsMatch(date, regex) || dateStringCheck(date) == true) {
      errors += "Date must be in a valid YYYY-MM-DD date format. ";
    }

    return errors;
  }

  /**
   * Validates month is less than 12, greater than 0, and days are 1 to 31.
   *
   * @param date
   * @return
   */
  public Boolean dateStringCheck(String date) {
    Boolean result = false;
    if (date.startsWith("00", 5) || date.substring(5, 7).compareTo(String.valueOf(12)) > 0) {
      result = true;
    }
    ;

    if (date.startsWith("00", 8) || date.substring(8, 10).compareTo(String.valueOf(31)) > 0) {
      result = true;
    }

    return result;
  }

}
