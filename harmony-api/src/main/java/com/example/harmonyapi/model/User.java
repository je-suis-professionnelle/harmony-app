package com.example.harmonyapi.model;

import com.example.harmonyapi.model.GroupUser;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "pseudo")
    private String pseudo;

    @Column(name = "mdp")
    private String mdp;

    public User() {

    }

    public User(String pseudo, String mdp)
    {
        this.mdp = mdp;
        this.pseudo = pseudo;
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
