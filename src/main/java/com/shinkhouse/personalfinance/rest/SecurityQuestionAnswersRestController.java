package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.SecurityQuestionAnswersModel;
import com.shinkhouse.personalfinance.repository.SecurityQuestionAnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/security-question-answers")
public class SecurityQuestionAnswersRestController {

    @Autowired
    private SecurityQuestionAnswersRepository securityQuestionAnswersRepository;

    // POST: Create a new security question answer
    @PostMapping
    public SecurityQuestionAnswersModel createAnswer(@RequestBody SecurityQuestionAnswersModel answer) {
        return securityQuestionAnswersRepository.save(answer);
    }

    // PUT: Update an existing security question answer by ID
    @PutMapping("/{id}")
    public ResponseEntity<SecurityQuestionAnswersModel> updateAnswer(@PathVariable Integer id,
            @RequestBody SecurityQuestionAnswersModel updatedAnswer) {
        Optional<SecurityQuestionAnswersModel> answerData = securityQuestionAnswersRepository.findById(id);

        if (answerData.isPresent()) {
            SecurityQuestionAnswersModel answer = answerData.get();
            answer.setQuestionId(updatedAnswer.getQuestionId());
            answer.setAnswer(updatedAnswer.getAnswer());
            answer.setCreatedOn(updatedAnswer.getCreatedOn());
            answer.setUpdatedOn(updatedAnswer.getUpdatedOn());
            return ResponseEntity.ok(securityQuestionAnswersRepository.save(answer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a security question answer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Integer id) {
        securityQuestionAnswersRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
