package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping()
    public ResponseEntity<?> getExpensesByIdGroup(@RequestParam(required = true) int idGroup, @RequestParam(required = false) String pseudo, @RequestParam(required = false) Long timestamp) {
        try {
            if(pseudo != null && !pseudo.isEmpty() && timestamp != null) {
                Expense expense = expenseRepository.findByPseudoAndTimestampAndIdGroup(pseudo, timestamp, idGroup);
                return new ResponseEntity<>(expense, HttpStatus.OK);
            } else {
                List<Expense> expenses = expenseRepository.findByIdGroup(idGroup);
                return new ResponseEntity<>(expenses, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createExpense(@RequestParam("expense") String expenseJson,
                                           @RequestParam(value = "imageBlob", required = false) MultipartFile imageBlob) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Expense expense = objectMapper.readValue(expenseJson, Expense.class);

            if (imageBlob != null && !imageBlob.isEmpty()) {
                String base64Image = Base64.getEncoder().encodeToString(imageBlob.getBytes());
                expense.setImageBlob(base64Image);
            }

            Expense savedExpense = expenseRepository.save(expense);
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Erreur de traitement de l'image", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur interne du serveur", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/expense")
    public ResponseEntity<?> deleteExpense(@RequestParam(required = true) String pseudo, @RequestParam(required = true) long timestamp, @RequestParam(required = true) long idGroup) {
        try {
            Expense expense = expenseRepository.findByPseudoAndTimestampAndIdGroup(pseudo, timestamp, idGroup);
            expenseRepository.delete(expense);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
