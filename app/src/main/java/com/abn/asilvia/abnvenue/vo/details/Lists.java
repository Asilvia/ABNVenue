
package com.abn.asilvia.abnvenue.vo.details;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lists {

    @SerializedName("groups")
    @Expose
    private List<Group_> groups = null;

    public List<Group_> getGroups() {
        return groups;
    }

    public void setGroups(List<Group_> groups) {
        this.groups = groups;
    }

}
