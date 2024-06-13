package io.medspot.api.domains.encounters;

import static io.medspot.api.constants.Paths.PATIENTS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for the encounter entity
 */
@RestController
@RequestMapping(value = PATIENTS_PATH)
public class EncounterController {

  @Autowired
  private EncounterService encounterService;


  @GetMapping(value = "/{id}/encounters")
  public ResponseEntity<List<Encounter>> getAllEncounters(@PathVariable Long id) {
    return new ResponseEntity<>(encounterService.getEncounters(id), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> getEncounterByID(
      @PathVariable Long id,
      @PathVariable Long encounterId
  ) {
    return new ResponseEntity<>(encounterService.getEncounterByID(id, encounterId), HttpStatus.OK);
  }


  @PostMapping(value = "/{id}/encounters")
  public ResponseEntity<Encounter> createEncounter(
      @PathVariable Long id,
      @RequestBody Encounter newEncounter
  ) {
    return new ResponseEntity<>(encounterService.createEncounter(id, newEncounter),
        HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> updateEncounter(
      @PathVariable Long id,
      @PathVariable Long encounterId,
      @RequestBody Encounter updateEncounter
  ) {
    return new ResponseEntity<>(encounterService.updateEncounter(id, encounterId, updateEncounter),
        HttpStatus.OK);
  }

}
