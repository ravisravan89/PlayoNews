package com.ravisravan.playonews.datamodels;

/**
 * Created by ravisravankumar on 15/10/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsModel implements Parcelable {
    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;
    @SerializedName("nbHits")
    @Expose
    private Integer nbHits;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("nbPages")
    @Expose
    private Integer nbPages;
    @SerializedName("hitsPerPage")
    @Expose
    private Integer hitsPerPage;
    @SerializedName("processingTimeMS")
    @Expose
    private Integer processingTimeMS;
    @SerializedName("exhaustiveNbHits")
    @Expose
    private Boolean exhaustiveNbHits;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("params")
    @Expose
    private String params;

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }

    public Integer getNbHits() {
        return nbHits;
    }

    public void setNbHits(Integer nbHits) {
        this.nbHits = nbHits;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public void setNbPages(Integer nbPages) {
        this.nbPages = nbPages;
    }

    public Integer getHitsPerPage() {
        return hitsPerPage;
    }

    public void setHitsPerPage(Integer hitsPerPage) {
        this.hitsPerPage = hitsPerPage;
    }

    public Integer getProcessingTimeMS() {
        return processingTimeMS;
    }

    public void setProcessingTimeMS(Integer processingTimeMS) {
        this.processingTimeMS = processingTimeMS;
    }

    public Boolean getExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public void setExhaustiveNbHits(Boolean exhaustiveNbHits) {
        this.exhaustiveNbHits = exhaustiveNbHits;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.hits);
        dest.writeValue(this.nbHits);
        dest.writeValue(this.page);
        dest.writeValue(this.nbPages);
        dest.writeValue(this.hitsPerPage);
        dest.writeValue(this.processingTimeMS);
        dest.writeValue(this.exhaustiveNbHits);
        dest.writeString(this.query);
        dest.writeString(this.params);
    }

    public NewsModel() {
    }

    protected NewsModel(Parcel in) {
        this.hits = in.createTypedArrayList(Hit.CREATOR);
        this.nbHits = (Integer) in.readValue(Integer.class.getClassLoader());
        this.page = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nbPages = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hitsPerPage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.processingTimeMS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.exhaustiveNbHits = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.query = in.readString();
        this.params = in.readString();
    }

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}

