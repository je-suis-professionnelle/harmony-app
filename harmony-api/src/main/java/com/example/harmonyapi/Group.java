package com.example.harmonyapi;

import com.example.harmonyapi.jdbc.postgresql.ExpenseId;
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

    @OneToMany(mappedBy = "group")
    private List<GroupUser> groupUsers;

    /*
    @OneToMany(mappedBy = "idGroup")
    private List<GroupUser> groupUsers;

     */

    /*@ElementCollection
    @Column(name = "pseudos")
    private List<String> pseudos;*/

    @Column(name = "title")
    private String title;

    @Column(name = "idOwner")
    private long idOwner;

    public Group() {

    }

    public Group(long idOwner, String title)
    {
        this.idOwner = idOwner;
        this.title = title;
    }

    public long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(long idOwner) {
        this.idOwner = idOwner;
    }

    /*
    public Group(List<String> pseudos, String title)
    {

        // this.pseudos = pseudos;
        this.title = title;
    }
    */

    public void addUser(User user) {
        if (groupUsers == null) {
            groupUsers = new ArrayList<>();
        }
        GroupUser groupUser = new GroupUser(this, user);

        groupUsers.add(groupUser);
    }

    /*public void addUser(long idUser) {
        if (groupUsers == null) {
            groupUsers = new ArrayList<>();
        }
        GroupUser groupUser = new GroupUser(identifiant, idUser);

        groupUsers.add(groupUser);
    }*/

    public long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(long identifiant) {
        this.identifiant = identifiant;
    }
    /*
    public List<String> getPseudos() {
        return this.pseudos;
    }

    public void setPseudos(List<String> pseudos) {
        this.pseudos = pseudos;
    }
    */
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
        Group group = (Group) obj;
        if (group.identifiant == this.identifiant) {
            return true;
        }
        return false;
    }

}
