package com.ravisravan.playonews.datamodels;

/**
 * Created by ravisravankumar on 15/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryText implements Parcelable {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("matchLevel")
    @Expose
    private String matchLevel;
    @SerializedName("matchedWords")
    @Expose
    private List<Object> matchedWords = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public void setMatchLevel(String matchLevel) {
        this.matchLevel = matchLevel;
    }

    public List<Object> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<Object> matchedWords) {
        this.matchedWords = matchedWords;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeString(this.matchLevel);
        dest.writeList(this.matchedWords);
    }

    public StoryText() {
    }

    protected StoryText(Parcel in) {
        this.value = in.readString();
        this.matchLevel = in.readString();
        this.matchedWords = new ArrayList<Object>();
        in.readList(this.matchedWords, Object.class.getClassLoader());
    }

    public static final Parcelable.Creator<StoryText> CREATOR = new Parcelable.Creator<StoryText>() {
        @Override
        public StoryText createFromParcel(Parcel source) {
            return new StoryText(source);
        }

        @Override
        public StoryText[] newArray(int size) {
            return new StoryText[size];
        }
    };
}
