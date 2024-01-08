package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import com.example.harmonyapi.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class LabelController {

    @Autowired
    LabelRepository labelRepository;
    @Autowired
    ExpenseRepository expenseRepository;


    @GetMapping("/labels")
    public ResponseEntity<List<Label>> getLabels(@RequestParam(required = true) long idGroup) {
        try {
            List<Label> labels = labelRepository.findByIdGroup(idGroup);
            return new ResponseEntity<>(labels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/label")
    public ResponseEntity<Label> createLabel(@RequestBody Label label) {
        try {
            if(labelRepository.existsByNameAndIdGroup(label.getName(), label.getIdGroup())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            Label savedLabel = labelRepository.save(label);
            return new ResponseEntity<>(savedLabel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/label")
    public ResponseEntity<?> deleteLabel(@RequestBody(required = true) Label label) {
        try {
            List<Expense> expensesWithLabel = expenseRepository.findByLabelAndIdGroup(label.getName(), label.getIdGroup());
            if (!expensesWithLabel.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            labelRepository.delete(label);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
