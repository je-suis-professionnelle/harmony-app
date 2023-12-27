package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173/") /* ça marche ce truc ??*/
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    ExpenseRepository expenseRepository;

    @GetMapping()
    public ResponseEntity<List<Expense>> getExpensesByIdGroup(@RequestParam(required = true) int idGroup) {
        try {
            List<Expense> expenses = expenseRepository.findByIdGroup(idGroup);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        try {
            Expense savedExpense = expenseRepository.save(expense);
            return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byPseudoTimestampAndIdGroup")
    public ResponseEntity<Expense> getExpensesByPseudoAndTimestampAndIdGroup(@RequestParam(required = true) String pseudo, @RequestParam(required = true) long timestamp, @RequestParam(required = true) long idGroup) {
        try {
            Expense expense = expenseRepository.findByPseudoAndTimestampAndIdGroup(pseudo, timestamp, idGroup);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byTimestamp")
    public ResponseEntity<List<Expense>> getExpensesByPseudoAndTimestamp(@RequestParam(required = true) String pseudo, @RequestParam(required = true) long timestamp) {
        try {
            List<Expense> expenses = expenseRepository.findByPseudoAndTimestamp(pseudo, timestamp);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byIdGroup")
    public ResponseEntity<List<Expense>> getExpensesByPseudoAndIdGroup(@RequestParam(required = true) String pseudo, @RequestParam(required = true) long idGroup) {
        try {
            List<Expense> expenses = expenseRepository.findByPseudoAndIdGroup(pseudo, idGroup);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byTimestampAndIdGroup")
    public ResponseEntity<List<Expense>> getExpensesByTimestampAndIdGroup(@RequestParam(required = true) long timestamp, @RequestParam(required = true) long idGroup) {
        try {
            List<Expense> expenses = expenseRepository.findByTimestampAndIdGroup(timestamp, idGroup);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
