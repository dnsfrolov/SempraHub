package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dnsfrolov on 14.03.2017.
 */

public class CreateAuthorization {

    @SerializedName("client_id")
    private String clientId = "36013411dd80f1f34896";
    @SerializedName("client_secret")
    private String clientSecret = "0779a77c13a86699890f2edf6fd8cb50d5f96d44";
    @SerializedName("scopes")
    private String[] scopes;
    @SerializedName("note")
    private String note;
    @SerializedName("note_url")
    private String noteUrl;
    @SerializedName("fingerprint")
    private String fingerprint;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String[] getScopes() {
        return scopes;
    }

    public String getNote() {
        return note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
