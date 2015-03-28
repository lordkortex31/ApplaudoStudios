package com.phunware.homework.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Venue implements Parcelable {

    private long mId;
    @SerializedName("pcode")
    private int mPcode;
    @SerializedName("latitude")
    private String mLatitude;
    @SerializedName("longitude")
    private String mLongitude;
    @SerializedName("name")
    private String mName;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("city")
    private String mCity;
    @SerializedName("state")
    private String mState;
    @SerializedName("zip")
    private String mZip;
    @SerializedName("phone")
    private String mPhone;

    @SerializedName("tollfreephone")
    private String mTollFreePhone;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("ticket_link")
    private String mTicketLink;
    @SerializedName("image_url")
    private String mImageUrl;


    @SerializedName("schedule")
    private List<ScheduleItemParceable> mSchedule;


    public Venue(Parcel in) {
        this.mId = in.readLong();
        this.mPcode = in.readInt();
        this.mLatitude = in.readString();
        this.mLongitude = in.readString();
        this.mName = in.readString();
        this.mAddress = in.readString();
        this.mCity = in.readString();
        this.mState = in.readString();
        this.mZip = in.readString();
        this.mPhone = in.readString();
        this.mTollFreePhone = in.readString();
        this.mUrl = in.readString();
        this.mDescription = in.readString();
        this.mTicketLink = in.readString();
        this.mImageUrl = in.readString();
        this.mSchedule = new ArrayList<>();
        in.readTypedList(mSchedule, ScheduleItemParceable.CREATOR);
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel source) {
            return new Venue(source);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(mId);
        dest.writeInt(mPcode);
        dest.writeString(mLatitude);
        dest.writeString(mLongitude);
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeString(mCity);
        dest.writeString(mState);
        dest.writeString(mZip);
        dest.writeString(mPhone);
        dest.writeString(mTollFreePhone);
        dest.writeString(mUrl);
        dest.writeString(mDescription);
        dest.writeString(mTicketLink);
        dest.writeString(mImageUrl);
        dest.writeTypedList(mSchedule);
    }


    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAddress() {
        return mAddress;
    }


    public String getCity() {
        return mCity;
    }


    public String getState() {
        return mState;
    }


    public String getZip() {
        return mZip;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public List<ScheduleItemParceable> getSchedule() {
        return mSchedule;
    }

}
