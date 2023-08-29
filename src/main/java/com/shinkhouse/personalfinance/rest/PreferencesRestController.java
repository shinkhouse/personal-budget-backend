package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.PreferencesModel;
import com.shinkhouse.personalfinance.repository.PreferencesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
public class PreferencesRestController {

    @Autowired
    private PreferencesRepository preferencesRepository;

    // POST: Create a new preference
    @PostMapping
    public PreferencesModel createPreference(@RequestBody PreferencesModel preference) {
        return preferencesRepository.save(preference);
    }

    // PUT: Update an existing preference by ID
    @PutMapping("/{id}")
    public ResponseEntity<PreferencesModel> updatePreference(@PathVariable Integer id,
            @RequestBody PreferencesModel updatedPreference) {
        Optional<PreferencesModel> preferenceData = preferencesRepository.findById(id);

        if (preferenceData.isPresent()) {
            PreferencesModel preference = preferenceData.get();
            preference.setUserId(updatedPreference.getUserId());
            preference.setSecurityQuestionId(updatedPreference.getSecurityQuestionId());
            preference.setCurrencyId(updatedPreference.getCurrencyId());
            preference.setCreatedOn(updatedPreference.getCreatedOn());
            preference.setUpdatedOn(updatedPreference.getUpdatedOn());
            return ResponseEntity.ok(preferencesRepository.save(preference));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a preference by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePreference(@PathVariable Integer id) {
        preferencesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
