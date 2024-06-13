package io.medspot.api.domains.encounters;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representation of an encounter object
 */
@Entity
public class Encounter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  Long patientId;

  String notes;

  String visitCode;

  String provider;

  String billingCode;

  String icd10;

  @Column(scale = 2)
  BigDecimal totalCost;

  @Column(scale = 2)
  BigDecimal copay;

  String chiefComplaint;

  Long pulse;

  Long systolic;

  Long diastolic;

  String date;

  public Encounter() {
  }

  public Encounter(Long patientId, String notes, String visitCode,
                   String provider,
      String billingCode, String icd10, BigDecimal totalCost,
                   BigDecimal copay,
      String chiefComplaint,
      Long pulse, Long systolic, Long diastolic, String date) {
    this.patientId = patientId;
    this.notes = notes;
    this.visitCode = visitCode;
    this.provider = provider;
    this.billingCode = billingCode;
    this.icd10 = icd10;
    this.totalCost = totalCost;
    this.copay = copay;
    this.chiefComplaint = chiefComplaint;
    this.pulse = pulse;
    this.systolic = systolic;
    this.diastolic = diastolic;
    this.date = date;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getVisitCode() {
    return visitCode;
  }

  public void setVisitCode(String visitCode) {
    this.visitCode = visitCode;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getBillingCode() {
    return billingCode;
  }

  public void setBillingCode(String billingCode) {
    this.billingCode = billingCode;
  }

  public String getIcd10() {
    return icd10;
  }

  public void setIcd10(String icd10) {
    this.icd10 = icd10;
  }

  public BigDecimal getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(BigDecimal totalCost) {
    this.totalCost = totalCost;
  }

  public BigDecimal getCopay() {
    return copay;
  }

  public void setCopay(BigDecimal copay) {
    this.copay = copay;
  }

  public String getChiefComplaint() {
    return chiefComplaint;
  }

  public void setChiefComplaint(String chiefComplaint) {
    this.chiefComplaint = chiefComplaint;
  }

  public Long getPulse() {
    return pulse;
  }

  public void setPulse(Long pulse) {
    this.pulse = pulse;
  }

  public Long getSystolic() {
    return systolic;
  }

  public void setSystolic(Long systolic) {
    this.systolic = systolic;
  }

  public Long getDiastolic() {
    return diastolic;
  }

  public void setDiastolic(Long diastolic) {
    this.diastolic = diastolic;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Encounter encounter = (Encounter) o;
    return Objects.equals(id, encounter.id) && Objects.equals(patientId,
        encounter.patientId) && Objects.equals(notes, encounter.notes)
        && Objects.equals(visitCode, encounter.visitCode) && Objects.equals(
        provider, encounter.provider) && Objects.equals(billingCode, encounter.billingCode)
        && Objects.equals(icd10, encounter.icd10) && Objects.equals(totalCost,
        encounter.totalCost) && Objects.equals(copay, encounter.copay)
        && Objects.equals(chiefComplaint, encounter.chiefComplaint)
        && Objects.equals(pulse, encounter.pulse) && Objects.equals(systolic,
        encounter.systolic) && Objects.equals(diastolic, encounter.diastolic)
        && Objects.equals(date, encounter.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, patientId, notes, visitCode, provider, billingCode, icd10, totalCost,
        copay, chiefComplaint, pulse, systolic, diastolic, date);
  }

  @Override
  public String toString() {
    return "Encounter{" +
        "id=" + id +
        ", patientId=" + patientId +
        ", notes='" + notes + '\'' +
        ", visitCode='" + visitCode + '\'' +
        ", provider='" + provider + '\'' +
        ", billingCode='" + billingCode + '\'' +
        ", icd10='" + icd10 + '\'' +
        ", totalCost=" + totalCost +
        ", copay=" + copay +
        ", chiefComplaint='" + chiefComplaint + '\'' +
        ", pulse=" + pulse +
        ", systolic=" + systolic +
        ", diastolic=" + diastolic +
        ", date=" + date +
        '}';
  }
}
