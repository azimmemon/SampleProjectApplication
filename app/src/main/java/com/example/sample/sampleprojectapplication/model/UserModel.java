package com.example.sample.sampleprojectapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by azimmemon on 02/07/19.
 */

public class UserModel {

    @SerializedName("login")
    private String name;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("avatar_url")
    private String imageUrl;

    //Only getters as we only want to read the data


    public String getName() {
        return name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
