package com.example.harmonyapi.model;

import com.example.harmonyapi.jpa.postgresql.GroupUserId;
import jakarta.persistence.*;

@Entity
@Table(name = "group_user")
@IdClass(GroupUserId.class)
public class GroupUser {

    @Id
    private String pseudoUser;

    @Id
    private long idGroup;

    public GroupUser() {

    }

    public GroupUser(String pseudoUser, long idGroup)
    {
        this.pseudoUser = pseudoUser;
        this.idGroup = idGroup;
    }

    public String getPseudoUser() {
        return this.pseudoUser;
    }

    public void setPseudoUser(String pseudoUser) {
        this.pseudoUser = pseudoUser;
    }

    public long getIdGroup() {
        return this.idGroup;
    }

    public void setIdGroup(long idGroup) {
        this.idGroup = idGroup;
    }

}
