package com.example.harmonyapi.model;

import com.example.harmonyapi.jpa.postgresql.GroupUserId;
import jakarta.persistence.*;

@Entity
@Table(name = "group_user")
@IdClass(GroupUserId.class)
public class GroupUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "idGroup", referencedColumnName = "identifiant")
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "pseudoUser", referencedColumnName = "pseudo")
    private User user;

    public GroupUser() {

    }

    public GroupUser(Group group, User user)
    {
        this.group = group;
        this.user = user;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    /*public GroupUser(long idGroup, long idUser)
    {
        this.idGroup = idGroup;
        this.idUser = idUser;
    }

    public long getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

    public long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }*/

}
