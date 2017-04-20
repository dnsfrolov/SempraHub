package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Denys on 25.02.2017.
 */

public class Repo {
    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String description;
    @SerializedName("language")
    private String language;
    @SerializedName("forks_count")
    private String forksCount;
    @SerializedName("stargazers_count")
    private String stargazersCount;
    @SerializedName("updated_at")
    private Date updatedAt;

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getForksCount() {
        return forksCount;
    }

    public String getStargazersCount() {
        return stargazersCount;
    }

    public String getFullName() {
        return fullName;
    }

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
