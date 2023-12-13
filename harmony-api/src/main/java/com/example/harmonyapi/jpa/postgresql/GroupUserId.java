package com.example.harmonyapi.jpa.postgresql;

import java.io.Serializable;

public class GroupUserId implements Serializable {

    private long group;
    private long user;

    public GroupUserId() {

    }

    public GroupUserId(long group, long user)
    {
        this.group = group;
        this.user = user;
    }

    @Override
    public int hashCode() {
        return (int) (this.group + this.user);
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

}
