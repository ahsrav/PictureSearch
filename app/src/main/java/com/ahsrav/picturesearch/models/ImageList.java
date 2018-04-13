package com.ahsrav.picturesearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageList {

    @SerializedName("totalHits")
    @Expose
    public Integer totalHits;
    @SerializedName("hits")
    @Expose
    public List<Hit> hits = null;
    @SerializedName("total")
    @Expose
    public Integer total;

}