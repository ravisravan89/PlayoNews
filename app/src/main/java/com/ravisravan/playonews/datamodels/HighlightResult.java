package com.ravisravan.playonews.datamodels;

/**
 * Created by ravisravankumar on 15/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HighlightResult implements Parcelable {

    @SerializedName("title")
    @Expose
    private Title title;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("story_text")
    @Expose
    private StoryText storyText;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public StoryText getStoryText() {
        return storyText;
    }

    public void setStoryText(StoryText storyText) {
        this.storyText = storyText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.title, flags);
        dest.writeParcelable(this.url, flags);
        dest.writeParcelable(this.author, flags);
        dest.writeParcelable(this.storyText, flags);
    }

    public HighlightResult() {
    }

    protected HighlightResult(Parcel in) {
        this.title = in.readParcelable(Title.class.getClassLoader());
        this.url = in.readParcelable(Url.class.getClassLoader());
        this.author = in.readParcelable(Author.class.getClassLoader());
        this.storyText = in.readParcelable(StoryText.class.getClassLoader());
    }

    public static final Parcelable.Creator<HighlightResult> CREATOR = new Parcelable.Creator<HighlightResult>() {
        @Override
        public HighlightResult createFromParcel(Parcel source) {
            return new HighlightResult(source);
        }

        @Override
        public HighlightResult[] newArray(int size) {
            return new HighlightResult[size];
        }
    };
}
