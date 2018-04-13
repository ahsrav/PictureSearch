package com.ahsrav.picturesearch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {

    @SerializedName("largeImageURL")
    @Expose
    public String largeImageURL;
    @SerializedName("webformatHeight")
    @Expose
    public Integer webformatHeight;
    @SerializedName("webformatWidth")
    @Expose
    public Integer webformatWidth;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("imageWidth")
    @Expose
    public Integer imageWidth;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("views")
    @Expose
    public Integer views;
    @SerializedName("comments")
    @Expose
    public Integer comments;
    @SerializedName("pageURL")
    @Expose
    public String pageURL;
    @SerializedName("imageHeight")
    @Expose
    public Integer imageHeight;
    @SerializedName("webformatURL")
    @Expose
    public String webformatURL;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("previewHeight")
    @Expose
    public Integer previewHeight;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("downloads")
    @Expose
    public Integer downloads;
    @SerializedName("user")
    @Expose
    public String user;
    @SerializedName("favorites")
    @Expose
    public Integer favorites;
    @SerializedName("imageSize")
    @Expose
    public Integer imageSize;
    @SerializedName("previewWidth")
    @Expose
    public Integer previewWidth;
    @SerializedName("userImageURL")
    @Expose
    public String userImageURL;
    @SerializedName("previewURL")
    @Expose
    public String previewURL;

}
