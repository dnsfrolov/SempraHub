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
    private String followers;
    @SerializedName("following")
    private String following;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("email")
    private String email;
    @SerializedName("company")
    private String company;
    @SerializedName("public_repos")
    private String publicRepos;
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("location")
    private String location;
    @SerializedName("bio")
    private String bio;
    @SerializedName("blog")
    private String blog;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public String getBlog() {
        return blog;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getFollowers() {
        return followers;
    }

    public String getFollowing() {
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

    public String getPublicRepos() {
        return publicRepos;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }
}
