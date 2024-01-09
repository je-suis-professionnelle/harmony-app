package com.example.harmonyapi.model;

import com.example.harmonyapi.jpa.postgresql.ExpenseId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identifiant")
    private long identifiant;

    @Column(name = "title")
    private String title;

    @Column(name = "idOwner")
    private String idOwner;

    public Group() {

    }

    public Group(String idOwner, String title)
    {
        this.idOwner = idOwner;
        this.title = title;
    }

    public String getPseudoOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
