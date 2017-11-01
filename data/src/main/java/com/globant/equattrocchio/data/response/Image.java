package com.globant.equattrocchio.data.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static android.R.attr.name;

@com.activeandroid.annotation.Table(name = "Images")
public class Image implements Parcelable{
    @Column(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @Column(name = "url")
    @SerializedName("url")
    @Expose
    private String url;
    @Column(name = "large_url")
    @SerializedName("large_url")
    @Expose
    private String largeUrl;
    @Column(name = "source_id")
    @SerializedName("source_id")
    @Expose
    private Object sourceId;
    @Column(name = "site")
    @SerializedName("site")
    @Expose
    private String site;

    protected Image(Parcel in) {
        url = in.readString();
        largeUrl = in.readString();
        site = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public Object getSourceId() {
        return sourceId;
    }

    public void setSourceId(Object sourceId) {
        this.sourceId = sourceId;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(largeUrl);
        parcel.writeString(site);
    }
}
