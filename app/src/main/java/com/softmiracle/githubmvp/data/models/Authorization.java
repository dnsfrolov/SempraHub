package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dnsfrolov on 14.03.2017.
 */

public class Authorization {

    @SerializedName("id")
    private String id;
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

    public String getId() {
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
}
