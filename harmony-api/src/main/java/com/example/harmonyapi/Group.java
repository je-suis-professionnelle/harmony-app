package com.example.harmonyapi;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identifiant")
    private long identifiant;

    @Column(name = "pseudos")
    private List<String> pseudos;

    @Column(name = "title")
    private String title;

    public Group() {

    }

    public Group(List<String> pseudos, String title)
    {
        this.pseudos = pseudos;
        this.title = title;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public List<String> getPseudos() {
        return this.pseudos;
    }

    public void setPseudos(List<String> pseudos) {
        this.pseudos = pseudos;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

}
