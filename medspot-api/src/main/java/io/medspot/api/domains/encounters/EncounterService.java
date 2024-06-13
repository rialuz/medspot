package io.medspot.api.domains.encounters;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Encounter Service
 */
public interface EncounterService {

  List<Encounter> getEncounters(Long id);

  Encounter getEncounterByID(Long id, Long encounterId);

  Encounter createEncounter(Long id, Encounter newEncounter);

  Encounter updateEncounter(Long id, Long encounterId, Encounter updatedEncounter);

}
