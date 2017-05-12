package com.softmiracle.githubmvp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dnsfrolov on 09.05.2017.
 */

public class Search<T> {

    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResult;
    @SerializedName("items")
    private List<T> items;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResult() {
        return incompleteResult;
    }

    public List<T> getItems() {
        return items;
    }
}
