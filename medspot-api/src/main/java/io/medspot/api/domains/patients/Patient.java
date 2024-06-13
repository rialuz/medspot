package io.medspot.api.domains.patients;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representation of a patient
 */
@Entity
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String firstName;

  String lastName;

  String ssn;

  String email;

  Long age;

  Long height;

  Long weight;

  String insurance;

  String gender;

  String street;

  String city;

  String state;

  String postal;

  public Patient() {
  }

  public Patient(String firstName, String lastName, String ssn, String email, Long age, Long height,
      Long weight, String insurance, String gender, String street, String city, String state,
      String postal) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.ssn = ssn;
    this.email = email;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.insurance = insurance;
    this.gender = gender;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postal = postal;
  }

  public Patient(Long id, String firstName, String lastName, String ssn, String email, Long age,
      Long height, Long weight, String insurance, String gender, String street, String city,
      String state, String postal) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.ssn = ssn;
    this.email = email;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.insurance = insurance;
    this.gender = gender;
    this.street = street;
    this.city = city;
    this.state = state;
    this.postal = postal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getAge() {
    return age;
  }

  public void setAge(Long age) {
    this.age = age;
  }

  public Long getHeight() {
    return height;
  }

  public void setHeight(Long height) {
    this.height = height;
  }

  public Long getWeight() {
    return weight;
  }

  public void setWeight(Long weight) {
    this.weight = weight;
  }

  public String getInsurance() {
    return insurance;
  }

  public void setInsurance(String insurance) {
    this.insurance = insurance;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostal() {
    return postal;
  }

  public void setPostal(String postal) {
    this.postal = postal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patient patient = (Patient) o;
    return Objects.equals(id, patient.id) && Objects.equals(firstName,
        patient.firstName) && Objects.equals(lastName, patient.lastName)
        && Objects.equals(ssn, patient.ssn) && Objects.equals(email,
        patient.email) && Objects.equals(age, patient.age) && Objects.equals(
        height, patient.height) && Objects.equals(weight, patient.weight)
        && Objects.equals(insurance, patient.insurance) && Objects.equals(gender,
        patient.gender) && Objects.equals(street, patient.street)
        && Objects.equals(city, patient.city) && Objects.equals(state,
        patient.state) && Objects.equals(postal, patient.postal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, ssn, email, age, height, weight, insurance, gender,
        street, city, state, postal);
  }

  @Override
  public String toString() {
    return "Patient{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", ssn='" + ssn + '\'' +
        ", email='" + email + '\'' +
        ", age=" + age +
        ", height=" + height +
        ", weight=" + weight +
        ", insurance='" + insurance + '\'' +
        ", gender='" + gender + '\'' +
        ", street='" + street + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        ", postal='" + postal + '\'' +
        '}';
  }
}
