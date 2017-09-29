package com.andnand.android.moivelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieFragment extends Fragment {

    private static final String ARG_MOVIE_ID = "movie_id";

    public static MovieFragment newInstance(UUID movieId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE_ID, movieId);

        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
