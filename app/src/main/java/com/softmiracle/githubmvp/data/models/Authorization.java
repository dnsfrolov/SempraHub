package com.softmiracle.githubmvp.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dnsfrolov on 14.03.2017.
 */

public class Authorization implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("token")
    private String token;
    @SerializedName("hashed_token")
    private String hashedToken;
    @SerializedName("token_last_eight")
    private String tokenLastEight;
    @SerializedName("note")
    private String note;
    @SerializedName("note_url")
    private String noteUrl;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("fingerprint")
    private String fingerprint;
    @SerializedName("scopes")
    private String[] scopes;

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getHashedToken() {
        return hashedToken;
    }

    public String getTokenLastEight() {
        return tokenLastEight;
    }

    public String getNote() {
        return note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String[] getScopes() {
        return scopes;
    }

    public Authorization() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.token);
        dest.writeString(this.hashedToken);
        dest.writeString(this.tokenLastEight);
        dest.writeString(this.note);
        dest.writeString(this.noteUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.fingerprint);
        dest.writeStringArray(this.scopes);
    }

    protected Authorization(Parcel in) {
        this.id = in.readInt();
        this.token = in.readString();
        this.hashedToken = in.readString();
        this.tokenLastEight = in.readString();
        this.note = in.readString();
        this.noteUrl = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.fingerprint = in.readString();
        this.scopes = in.createStringArray();
    }

    public static final Creator<Authorization> CREATOR = new Creator<Authorization>() {
        @Override
        public Authorization createFromParcel(Parcel source) {
            return new Authorization(source);
        }

        @Override
        public Authorization[] newArray(int size) {
            return new Authorization[size];
        }
    };
}
