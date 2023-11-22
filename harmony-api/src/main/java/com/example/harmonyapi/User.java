package com.example.harmonyapi;

public class User {

    private String identifiant;
    private String mdp;
    private String pseudo;

    public User() {

    }

    public User(String identifiant, String mdp, String pseudo)
    {
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.pseudo = pseudo;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
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
