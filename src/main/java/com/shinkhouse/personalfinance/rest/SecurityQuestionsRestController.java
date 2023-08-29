package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.SecurityQuestionsModel;
import com.shinkhouse.personalfinance.repository.SecurityQuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/security-questions")
public class SecurityQuestionsRestController {

    @Autowired
    private SecurityQuestionsRepository securityQuestionsRepository;

    // POST: Create a new security question
    @PostMapping
    public SecurityQuestionsModel createQuestion(@RequestBody SecurityQuestionsModel question) {
        return securityQuestionsRepository.save(question);
    }

    // PUT: Update an existing security question by ID
    @PutMapping("/{id}")
    public ResponseEntity<SecurityQuestionsModel> updateQuestion(@PathVariable Integer id,
            @RequestBody SecurityQuestionsModel updatedQuestion) {
        Optional<SecurityQuestionsModel> questionData = securityQuestionsRepository.findById(id);

        if (questionData.isPresent()) {
            SecurityQuestionsModel question = questionData.get();
            question.setQuestion(updatedQuestion.getQuestion());
            question.setCreatedOn(updatedQuestion.getCreatedOn());
            question.setUpdatedOn(updatedQuestion.getUpdatedOn());
            return ResponseEntity.ok(securityQuestionsRepository.save(question));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a security question by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        securityQuestionsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
