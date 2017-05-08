package com.softmiracle.githubmvp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Denys on 25.02.2017.
 */

public class User implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.name);
        dest.writeString(this.followers);
        dest.writeString(this.following);
        dest.writeString(this.avatar);
        dest.writeString(this.email);
        dest.writeString(this.company);
        dest.writeString(this.publicRepos);
        dest.writeString(this.gistsUrl);
        dest.writeString(this.starredUrl);
        dest.writeLong(this.createdAt != null ? this.createdAt.getTime() : -1);
        dest.writeString(this.location);
        dest.writeString(this.bio);
        dest.writeString(this.blog);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.name = in.readString();
        this.followers = in.readString();
        this.following = in.readString();
        this.avatar = in.readString();
        this.email = in.readString();
        this.company = in.readString();
        this.publicRepos = in.readString();
        this.gistsUrl = in.readString();
        this.starredUrl = in.readString();
        long tmpCreatedAt = in.readLong();
        this.createdAt = tmpCreatedAt == -1 ? null : new Date(tmpCreatedAt);
        this.location = in.readString();
        this.bio = in.readString();
        this.blog = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
