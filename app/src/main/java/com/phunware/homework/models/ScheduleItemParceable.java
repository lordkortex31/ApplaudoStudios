package com.phunware.homework.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac on 28/03/15.
 * Parceable class for Schedule Items.
 */
public class ScheduleItemParceable implements Parcelable {

    @SerializedName("start_date")
    private String mStartDate;

    @SerializedName("end_date")
    private String mEndDate;

    public ScheduleItemParceable(Parcel in){
        this.mStartDate = in.readString();
        this.mEndDate = in.readString();
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mStartDate);
        dest.writeString(mEndDate);
    }

    public static final Creator<ScheduleItemParceable> CREATOR = new Creator<ScheduleItemParceable>() {
        @Override
        public ScheduleItemParceable createFromParcel(Parcel source) {
            return new ScheduleItemParceable(source);
        }

        @Override
        public ScheduleItemParceable[] newArray(int size) {
            return new ScheduleItemParceable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    public String getEndDate() {
        return mEndDate;
    }


    public String getStartDate() {
        return mStartDate;
    }


}
