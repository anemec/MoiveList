package com.andnand.android.moivelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andrew on 9/27/17.
 */

public class MainMovieListFragment extends Fragment {

    private RecyclerView mMovieRecyclerView;
    MovieAdapter mAdapter;

    public static MainMovieListFragment newInstance() {

        return new MainMovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_movie_gallery, container, false);

        mMovieRecyclerView = view.findViewById(R.id.fragment_main_movie_gallery_recycler_view);
        mMovieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        updateUI();

        return view;
    }

    public void updateUI() {
        MovieSingleton movieSingleton = MovieSingleton.get(getActivity());
        List<Movie> movies = movieSingleton.getMovies();

        if (mAdapter == null) {
            mAdapter = new MovieAdapter(movies);
        } else {
            mAdapter.setMovies(movies);
            mAdapter.notifyDataSetChanged();
        }
    }


    private class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

        private List<Movie> mMovies;

        public MovieAdapter(List<Movie> movies) {
            mMovies = movies;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.movie_item, parent, false);
            return new MovieHolder(view);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            Movie movie = mMovies.get(position);
            holder.bindMovie(movie);
        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }

        public void setMovies(List<Movie> movies) {
            mMovies = movies;
        }
    }

    private class MovieHolder extends RecyclerView.ViewHolder {

        private Movie mMovie;

        public MovieHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
        }

        public void bindMovie(Movie movie) {

        }



    }


}
























