package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.PaymentTypesModel;
import com.shinkhouse.personalfinance.repository.PaymentTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/payment-types")
public class PaymentTypesRestController {

    @Autowired
    private PaymentTypesRepository paymentTypesRepository;

    // POST: Create a new payment type
    @PostMapping
    public PaymentTypesModel createPaymentType(@RequestBody PaymentTypesModel paymentType) {
        return paymentTypesRepository.save(paymentType);
    }

    // PUT: Update an existing payment type by ID
    @PutMapping("/{id}")
    public ResponseEntity<PaymentTypesModel> updatePaymentType(@PathVariable Integer id,
            @RequestBody PaymentTypesModel updatedPaymentType) {
        Optional<PaymentTypesModel> paymentTypeData = paymentTypesRepository.findById(id);

        if (paymentTypeData.isPresent()) {
            PaymentTypesModel paymentType = paymentTypeData.get();
            paymentType.setDescription(updatedPaymentType.getDescription());
            paymentType.setIsDefault(updatedPaymentType.getIsDefault());
            paymentType.setType(updatedPaymentType.getType());
            paymentType.setCreatedOn(updatedPaymentType.getCreatedOn());
            paymentType.setCreatedBy(updatedPaymentType.getCreatedBy());
            paymentType.setUpdatedOn(updatedPaymentType.getUpdatedOn());
            paymentType.setUpdatedBy(updatedPaymentType.getUpdatedBy());
            return ResponseEntity.ok(paymentTypesRepository.save(paymentType));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a payment type by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentType(@PathVariable Integer id) {
        paymentTypesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
