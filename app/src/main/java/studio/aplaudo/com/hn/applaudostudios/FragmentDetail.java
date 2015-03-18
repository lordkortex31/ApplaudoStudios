package studio.aplaudo.com.hn.applaudostudios;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import studio.aplaudo.com.hn.model.ScheduleItem;
import studio.aplaudo.com.hn.model.Venue;

/**
 * Created by CortesMoncada on 17/03/2015.
 * Fragment used to show detail Information.
 */
public class FragmentDetail extends Fragment {


    private static final String SEPARATOR = " , ";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private ImageView mImgAmazonList;
    private TextView mTxvAmazonList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mImgAmazonList = (ImageView) getActivity().findViewById(R.id.imgAmazonDetailView);
        mTxvAmazonList = (TextView) getActivity().findViewById(R.id.txvAmazonDetailDescription);

    }

    public void changeData(Venue mVenue) {

        mImgAmazonList = (ImageView) getActivity().findViewById(R.id.imgAmazonDetailView);
        mTxvAmazonList = (TextView) getActivity().findViewById(R.id.txvAmazonDetailDescription);


        if (mVenue != null) {
            String mUrlImage = mVenue.getImageUrl();

            if (!mUrlImage.equals("")) {
                Picasso.with(getActivity()).load(mUrlImage).error(R.mipmap.noimage).into(mImgAmazonList);
            } else {
                Resources res = getActivity().getResources();
                Drawable drawable = res.getDrawable(R.mipmap.noimage);
                mImgAmazonList.setImageDrawable(drawable);
            }

            StringBuilder strBuilderDescription = buildDescription(mVenue);
            String strComplexDate = getComplexDate(mVenue);
            strBuilderDescription.append(strComplexDate);

            mTxvAmazonList.setText(strBuilderDescription.toString());
        }
    }


    private StringBuilder buildDescription(Venue mVenue) {

        StringBuilder strBuilderDescription = new StringBuilder();
        strBuilderDescription.append(mVenue.getName());
        strBuilderDescription.append(SEPARATOR);
        strBuilderDescription.append(mVenue.getDescription());
        strBuilderDescription.append(LINE_SEPARATOR);
        strBuilderDescription.append(LINE_SEPARATOR);
        strBuilderDescription.append(mVenue.getAddress());
        strBuilderDescription.append(LINE_SEPARATOR);
        strBuilderDescription.append(mVenue.getCity());
        strBuilderDescription.append(SEPARATOR);
        strBuilderDescription.append(mVenue.getState());
        strBuilderDescription.append(SEPARATOR);
        strBuilderDescription.append(mVenue.getZip());
        strBuilderDescription.append(LINE_SEPARATOR);
        strBuilderDescription.append(LINE_SEPARATOR);

        return strBuilderDescription;

    }


    private String buildDate(final String sStartDate, final String sEndDate) {

        StringBuilder strBuilderDate = new StringBuilder();
        strBuilderDate.append(sStartDate);
        strBuilderDate.append(LINE_SEPARATOR);
        strBuilderDate.append(sEndDate);
        strBuilderDate.append(LINE_SEPARATOR);

        return strBuilderDate.toString();

    }


    private String getComplexDate(Venue mVenue) {
        StringBuilder strComplexDate = new StringBuilder();
        for (ScheduleItem mSchedule : mVenue.getSchedule()) {
            if (mSchedule != null) {
                String sStartDate = mSchedule.getStartDate();
                String sEndDate = mSchedule.getEndDate();
                String sComplexDate = buildDate(sStartDate, sEndDate);
                strComplexDate.append(sComplexDate);
            }
        }
        return strComplexDate.toString();

    }
}
