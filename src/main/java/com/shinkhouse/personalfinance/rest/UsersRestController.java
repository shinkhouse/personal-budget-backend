package com.shinkhouse.personalfinance.rest;

import com.shinkhouse.personalfinance.model.UsersModel;
import com.shinkhouse.personalfinance.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersRestController {

    @Autowired
    private UsersRepository usersRepository;

    // POST: Create a new user
    @PostMapping
    public UsersModel createUser(@RequestBody UsersModel user) {
        return usersRepository.save(user);
    }

    // PUT: Update an existing user by ID
    @PutMapping("/{id}")
    public ResponseEntity<UsersModel> updateUser(@PathVariable Integer id, @RequestBody UsersModel updatedUser) {
        Optional<UsersModel> userData = usersRepository.findById(id);

        if (userData.isPresent()) {
            UsersModel user = userData.get();
            user.setUserId(updatedUser.getUserId());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setFullName(updatedUser.getFullName());
            user.setIsLoggedIn(updatedUser.getIsLoggedIn());
            user.setLastLoggedOn(updatedUser.getLastLoggedOn());
            user.setCreatedOn(updatedUser.getCreatedOn());
            user.setUpdatedOn(updatedUser.getUpdatedOn());
            user.setCreatedBy(updatedUser.getCreatedBy());
            user.setUpdatedBy(updatedUser.getUpdatedBy());
            return ResponseEntity.ok(usersRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usersRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
