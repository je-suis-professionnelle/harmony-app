package com.example.harmonyapi.jpa.postgresql;

import com.example.harmonyapi.model.User;

import java.io.Serializable;

public class GroupUserId implements Serializable {

    private String pseudoUser;
    private long idGroup;

    public GroupUserId() {
    }

    public GroupUserId(String pseudoUser, long idGroup)
    {
        this.pseudoUser = pseudoUser;
        this.idGroup = idGroup;
    }

}
