package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Denys on 25.02.2017.
 */

public class Repo {
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}
