package com.example.harmonyapi;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identifiant")
    private long identifiant;

    @OneToMany(mappedBy = "user")
    private List<GroupUser> groupUsers;

    /*
    @OneToMany(mappedBy = "idUser")
    private List<GroupUser> groupUsers;

     */

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

    public void addGroup(Group group) {
        if (groupUsers == null) {
            groupUsers = new ArrayList<>();
        }
        GroupUser groupUser = new GroupUser(group, this);

        groupUsers.add(groupUser);
    }

    /*
    public void addGroup(long idGroup) {
        if (groupUsers == null) {
            groupUsers = new ArrayList<>();
        }
        GroupUser groupUser = new GroupUser(idGroup, identifiant);

        groupUsers.add(groupUser);
    }

     */
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
