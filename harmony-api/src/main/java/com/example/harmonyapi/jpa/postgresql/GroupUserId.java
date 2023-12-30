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

    /*
    private long group;
    private User user;

    public GroupUserId() {

    }

    public GroupUserId(long group, User user)
    {
        this.group = group;
        this.user = user;
    }

    @Override
    public int hashCode() {
        return (int) (this.group + this.user.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GroupUserId)) {
            return false;
        }
        GroupUserId groupUserId = (GroupUserId) obj;
        if (groupUserId.group == this.group && groupUserId.user == this.user) {
            return true;
        }
        return false;
    }
*/
}
