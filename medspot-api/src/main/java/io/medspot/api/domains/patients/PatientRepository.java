package io.medspot.api.domains.patients;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Patient repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

  Patient findByEmail(String email);

}
