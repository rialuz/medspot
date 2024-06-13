package io.medspot.api.domains.encounters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Encounter repository
 */
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {

}
