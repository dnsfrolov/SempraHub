package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Denys on 25.02.2017.
 */

public class User {
    @SerializedName("login")
    private String login;
    @SerializedName("name")
    private String name;
    @SerializedName("followers")
    private int followers;
    @SerializedName("following")
    private int following;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("email")
    private String email;
    @SerializedName("company")
    private String company;
    @SerializedName("public_repos")
    private int publicRepos;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("created_at")
    private String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }
}
