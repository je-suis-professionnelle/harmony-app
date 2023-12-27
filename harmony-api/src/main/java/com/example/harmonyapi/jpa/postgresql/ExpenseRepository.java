package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, ExpenseId> {

    public List<Expense> findByIdGroup(int idGroup);
    public Expense findByPseudoAndTimestampAndIdGroup(String pseudo, long timestamp, long idGroup);
    public List<Expense> findByPseudoAndTimestamp(String pseudo, long timestamp);
    public List<Expense> findByPseudoAndIdGroup(String pseudo, long idGroup);
    public List<Expense> findByTimestampAndIdGroup(long timestamp, long idGroup);
}
