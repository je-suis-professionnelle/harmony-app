package com.example.harmonyapi;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identifiant")
    private long identifiant;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "pseudo")
    private String pseudo;

    public User() {

    }

    public User(String mdp, String pseudo)
    {
        this.mdp = mdp;
        this.pseudo = pseudo;
    }

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return pseudo.toUpperCase().toCharArray()[0]+pseudo.substring(1);
    }

}
