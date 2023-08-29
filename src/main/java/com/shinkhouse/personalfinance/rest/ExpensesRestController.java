package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.ExpensesModel;
import com.shinkhouse.personalfinance.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesRestController {

    @Autowired
    private ExpensesRepository expensesRepository;

    // POST: Create a new expense
    @PostMapping
    public ExpensesModel createExpense(@RequestBody ExpensesModel expense) {
        return expensesRepository.save(expense);
    }

    // PUT: Update an existing expense by ID
    @PutMapping("/{id}")
    public ResponseEntity<ExpensesModel> updateExpense(@PathVariable Integer id,
            @RequestBody ExpensesModel updatedExpense) {
        Optional<ExpensesModel> expenseData = expensesRepository.findById(id);

        if (expenseData.isPresent()) {
            ExpensesModel expense = expenseData.get();
            expense.setUserId(updatedExpense.getUserId());
            expense.setDescription(updatedExpense.getDescription());
            expense.setDated(updatedExpense.getDated());
            expense.setAmount(updatedExpense.getAmount());
            expense.setPaymentType(updatedExpense.getPaymentType());
            expense.setCategory(updatedExpense.getCategory());
            expense.setTag(updatedExpense.getTag());
            expense.setSubDescription(updatedExpense.getSubDescription());
            expense.setCreatedOn(updatedExpense.getCreatedOn());
            expense.setCreatedBy(updatedExpense.getCreatedBy());
            expense.setUpdatedOn(updatedExpense.getUpdatedOn());
            expense.setUpdatedBy(updatedExpense.getUpdatedBy());
            return ResponseEntity.ok(expensesRepository.save(expense));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete an expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Integer id) {
        expensesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
