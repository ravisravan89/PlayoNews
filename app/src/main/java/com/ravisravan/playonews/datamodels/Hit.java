package com.ravisravan.playonews.datamodels;

/**
 * Created by ravisravankumar on 15/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit implements Parcelable {

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("story_text")
    @Expose
    private String storyText;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("story_id")
    @Expose
    private Long storyId;
    @SerializedName("story_title")
    @Expose
    private String storyTitle;
    @SerializedName("story_url")
    @Expose
    private String storyUrl;
    @SerializedName("parent_id")
    @Expose
    private Long parentId;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("_tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("objectID")
    @Expose
    private String objectID;
    @SerializedName("_highlightResult")
    @Expose
    private HighlightResult highlightResult;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getStoryText() {
        return storyText;
    }

    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    public Object getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public Object getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public Object getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public Object getStoryUrl() {
        return storyUrl;
    }

    public void setStoryUrl(String storyUrl) {
        this.storyUrl = storyUrl;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public void setCreatedAtI(Integer createdAtI) {
        this.createdAtI = createdAtI;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public HighlightResult getHighlightResult() {
        return highlightResult;
    }

    public void setHighlightResult(HighlightResult highlightResult) {
        this.highlightResult = highlightResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdAt);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.author);
        dest.writeValue(this.points);
        dest.writeString(this.storyText);
        dest.writeString(this.commentText);
        dest.writeValue(this.numComments);
        dest.writeLong(this.storyId);
        dest.writeString(this.storyTitle);
        dest.writeString(this.storyUrl);
        dest.writeLong(this.parentId);
        dest.writeValue(this.createdAtI);
        dest.writeStringList(this.tags);
        dest.writeString(this.objectID);
        dest.writeParcelable(this.highlightResult, flags);
    }

    public Hit() {
    }

    protected Hit(Parcel in) {
        this.createdAt = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.author = in.readString();
        this.points = (Integer) in.readValue(Integer.class.getClassLoader());
        this.storyText = in.readString();
        this.commentText = in.readParcelable(Object.class.getClassLoader());
        this.numComments = (Integer) in.readValue(Integer.class.getClassLoader());
        this.storyId = in.readParcelable(Object.class.getClassLoader());
        this.storyTitle = in.readParcelable(Object.class.getClassLoader());
        this.storyUrl = in.readParcelable(Object.class.getClassLoader());
        this.parentId = in.readParcelable(Object.class.getClassLoader());
        this.createdAtI = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.objectID = in.readString();
        this.highlightResult = in.readParcelable(HighlightResult.class.getClassLoader());
    }

    public static final Parcelable.Creator<Hit> CREATOR = new Parcelable.Creator<Hit>() {
        @Override
        public Hit createFromParcel(Parcel source) {
            return new Hit(source);
        }

        @Override
        public Hit[] newArray(int size) {
            return new Hit[size];
        }
    };
}
