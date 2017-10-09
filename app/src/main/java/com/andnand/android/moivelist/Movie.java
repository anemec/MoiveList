package com.andnand.android.moivelist;

import android.util.Log;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by andrew on 9/28/17.
 */

//TODO decide between Serializable vs Parcelable
public class Movie implements Serializable {

    private UUID mUUID;
    private String mTitle;
    private String mYear;
    private String mRated;
    private String mRuntime;
    private String mGenre;
    private String mDirector;
    private String mWriter;
    private String mActors;
    private String mLanguage = "N/A";
    private String mPoster;
    private String mRatings;
    private String mImdbId;
    private String mProduction;
    private String mWebsite;

    public Movie() {
        this(UUID.randomUUID());
    }

    public Movie(UUID uuid) {
        mUUID = uuid;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
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

    public String getRated() {
        return mRated;
    }

    public void setRated(String rated) {
        mRated = rated;
    }

    public String getRuntime() {
        return mRuntime;
    }

    public void setRuntime(String runtime) {
        mRuntime = runtime;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getDirector() {
        return mDirector;
    }

    public void setDirector(String director) {
        mDirector = director;
    }

    public String getWriter() {
        return mWriter;
    }

    public void setWriter(String writer) {
        mWriter = writer;
    }

    public String getActors() {
        return mActors;
    }

    public void setActors(String actors) {
        mActors = actors;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getRatings() {
        return mRatings;
    }

    public void setRatings(String ratings) {
        mRatings = ratings;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getProduction() {
        return mProduction;
    }

    public void setProduction(String production) {
        mProduction = production;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    @Override
    public String toString() {
        if (mTitle != null)
            return "Movie title " + mTitle;
        else
            return "Movie title is null";
    }
}
