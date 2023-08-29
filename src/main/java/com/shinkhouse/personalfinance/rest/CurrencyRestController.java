package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.CurrencyModel;
import com.shinkhouse.personalfinance.repository.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyRestController {

    @Autowired
    private CurrencyRepository currencyRepository;

    // POST: Create a new currency
    @PostMapping
    public CurrencyModel createCurrency(@RequestBody CurrencyModel currency) {
        return currencyRepository.save(currency);
    }

    // PUT: Update an existing currency by ID
    @PutMapping("/{id}")
    public ResponseEntity<CurrencyModel> updateCurrency(@PathVariable Integer id,
            @RequestBody CurrencyModel updatedCurrency) {
        Optional<CurrencyModel> currencyData = currencyRepository.findById(id);

        if (currencyData.isPresent()) {
            CurrencyModel currency = currencyData.get();
            currency.setName(updatedCurrency.getName());
            currency.setCreatedOn(updatedCurrency.getCreatedOn());
            currency.setUpdatedOn(updatedCurrency.getUpdatedOn());
            return ResponseEntity.ok(currencyRepository.save(currency));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a currency by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Integer id) {
        currencyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
