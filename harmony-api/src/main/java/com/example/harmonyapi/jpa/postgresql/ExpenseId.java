package com.example.harmonyapi.jpa.postgresql;

import java.io.Serializable;

public class ExpenseId implements Serializable {
    private String pseudo;
    private long timestamp;
    private long idGroup;

    public ExpenseId() {

    }

    public ExpenseId(String pseudo, long timestamp, long idGroup) {
        this.pseudo = pseudo;
        this.timestamp = timestamp;
        this.idGroup = idGroup;
    }

    @Override
    public int hashCode() {
        return (int) (this.timestamp + this.idGroup + this.pseudo.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ExpenseId)) {
            return false;
        }
        ExpenseId expenseId = (ExpenseId) obj;
        if (expenseId.pseudo.equals(this.pseudo) && expenseId.timestamp == this.timestamp && this.idGroup == expenseId.idGroup) {
            return true;
        }
        return false;
    }
}
