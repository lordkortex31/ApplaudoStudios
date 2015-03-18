package studio.aplaudo.com.hn.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;

public class ScheduleItem implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @SerializedName("start_date")
    private String mStartDate;
    @SerializedName("end_date")
    private String mEndDate;

    public ScheduleItem() {

    }

    public ScheduleItem(String startDate, String endDate) {
        mStartDate = startDate;
        mEndDate = endDate;
    }


    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate1(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        mStartDate = startDate;
    }

    public void setEndDate1(String endDate) throws ParseException {
        mEndDate = endDate;
    }


    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o instanceof ScheduleItem) {
            result = mStartDate.equals(((ScheduleItem) o).getStartDate())
                    && mEndDate.equals(((ScheduleItem) o).getEndDate());
        }
        return result;
    }


}