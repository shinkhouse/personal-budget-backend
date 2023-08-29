package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.IncomeModel;
import com.shinkhouse.personalfinance.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/income")
public class IncomeRestController {

    @Autowired
    private IncomeRepository incomeRepository;

    // POST: Create a new income
    @PostMapping
    public IncomeModel createIncome(@RequestBody IncomeModel income) {
        return incomeRepository.save(income);
    }

    // PUT: Update an existing income by ID
    @PutMapping("/{id}")
    public ResponseEntity<IncomeModel> updateIncome(@PathVariable Integer id, @RequestBody IncomeModel updatedIncome) {
        Optional<IncomeModel> incomeData = incomeRepository.findById(id);

        if (incomeData.isPresent()) {
            IncomeModel income = incomeData.get();
            income.setUserId(updatedIncome.getUserId());
            income.setDescription(updatedIncome.getDescription());
            income.setAmount(updatedIncome.getAmount());
            income.setDated(updatedIncome.getDated());
            income.setPaymentTypeId(updatedIncome.getPaymentTypeId());
            income.setTag(updatedIncome.getTag());
            income.setSubDescription(updatedIncome.getSubDescription());
            income.setCreatedOn(updatedIncome.getCreatedOn());
            income.setCreatedBy(updatedIncome.getCreatedBy());
            income.setUpdatedOn(updatedIncome.getUpdatedOn());
            income.setUpdatedBy(updatedIncome.getUpdatedBy());
            return ResponseEntity.ok(incomeRepository.save(income));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete an income by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Integer id) {
        incomeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
