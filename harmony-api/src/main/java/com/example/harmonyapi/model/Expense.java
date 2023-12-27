package com.example.harmonyapi.model;

import com.example.harmonyapi.jpa.postgresql.ExpenseId;
import jakarta.persistence.*;

@Entity
@Table(name = "expenses")
@IdClass(ExpenseId.class)
public class Expense {

    @Id
    @Column(name = "pseudo")
    private String pseudo;

    @Id
    @Column(name = "timestamp")
    private long timestamp;

    @Id
    @Column(name = "idGroup")
    private long idGroup;

    @Column(name = "amount")
    private double amount;

    @Column(name = "label")
    private String label;

    @Column(name = "description")
    private String description;

    public Expense() {

    }

    public Expense(String pseudo, long timestamp, long idGroup, double amount, String label, String description)
    {
        this.pseudo = pseudo;
        this.timestamp = timestamp;
        this.idGroup = idGroup;
        this.amount = amount;
        this.label = label;
        this.description = description;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
