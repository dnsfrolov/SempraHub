package com.softmiracle.githubmvp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Denys on 25.02.2017.
 */

public class Repo implements Parcelable {
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
    @SerializedName("fork")
    private boolean isFork;
    @SerializedName("open_issues_count")
    private String openIssuesCount;
    @SerializedName("watchers_count")
    private String watchersCount;

    private User owner;

    public User getOwner() {
        return owner;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public String getOpenIssuesCount() {
        return openIssuesCount;
    }

    public boolean isFork() {
        return isFork;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.fullName);
        dest.writeString(this.description);
        dest.writeString(this.language);
        dest.writeString(this.forksCount);
        dest.writeString(this.stargazersCount);
        dest.writeString(this.openIssuesCount);
        dest.writeLong(this.updatedAt != null ? this.updatedAt.getTime() : -1);
        dest.writeByte(this.isFork ? (byte) 1 : (byte) 0);
    }

    public Repo() {
    }

    protected Repo(Parcel in) {
        this.name = in.readString();
        this.fullName = in.readString();
        this.description = in.readString();
        this.language = in.readString();
        this.forksCount = in.readString();
        this.stargazersCount = in.readString();
        this.openIssuesCount = in.readString();
        long tmpUpdatedAt = in.readLong();
        this.updatedAt = tmpUpdatedAt == -1 ? null : new Date(tmpUpdatedAt);
        this.isFork = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Repo> CREATOR = new Parcelable.Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel source) {
            return new Repo(source);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };
}
