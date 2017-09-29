package com.andnand.android.moivelist;

/**
 * Created by andrew on 9/7/17.
 */

public class MoveItem {
    private String mTitle;
    private String mYear;
    private String mImage;

    @Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }
}
