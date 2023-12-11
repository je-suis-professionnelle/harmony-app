package com.example.harmonyapi;

import java.io.Serializable;

public class GroupUserId implements Serializable {

    private long group;
    private long user;

    /*private long idGroup;
    private long idUser;

     */

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

    /*public GroupUserId(long idGroup, long idUser)
    {
        this.idGroup = idGroup;
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        return (int) (this.idGroup + this.idUser);
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
        if (groupUserId.idGroup == this.idGroup && groupUserId.idUser == this.idUser) {
            return true;
        }
        return false;
    }

     */
}
