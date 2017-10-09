package com.andnand.android.moivelist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andrew on 9/27/17.
 */

public class MainMovieListFragment extends Fragment {

    private static final String LOG_TAG = "MainMovieListFragment";

    private RecyclerView mMovieRecyclerView;
    private Callbacks mCallBacks;
    MovieAdapter mAdapter;

    public static MainMovieListFragment newInstance() {

        return new MainMovieListFragment();
    }

    public interface Callbacks {
        void onMovieSelected(Movie movie);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallBacks = (Callbacks) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_main_add_new:
                Intent intent = new Intent(getContext(), MovieGalleryActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI() {
        MovieSingleton movieSingleton = MovieSingleton.get(getActivity());
        List<Movie> movies = movieSingleton.getMovies();
        Log.i(LOG_TAG, "Movies length " + movies.size());
        Log.i(LOG_TAG, "First movie poster " + movies.get(0).getPoster());

        if (mAdapter == null) {
            mAdapter = new MovieAdapter(movies);
            mMovieRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setMovies(movies);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //Adapter
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
    //End adapter

    //Holder
    private class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private Movie mMovie;
        private ImageView mMovieImageView;

        public MovieHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            mMovieImageView = itemView.findViewById(R.id.fragment_moive_item_image_view);
        }

        public void bindMovie(Movie movie) {
            mMovie = movie;
            Log.i(LOG_TAG, "Movie poster is " + mMovie.getPoster());
            Log.i(LOG_TAG, "Movie title is " + mMovie.getTitle());
            Picasso.with(getActivity())
                    .load(mMovie.getPoster())
                    .placeholder(R.drawable.github)
                    .into(mMovieImageView);
        }

        @Override
        public void onClick(View view) {
            mCallBacks.onMovieSelected(mMovie);
        }

        //TODO clean up
        @Override
        public boolean onLongClick(View view) {

            //mMovieImageView.setColorFilter(Color.argb(200,200,200,200));

            new AlertDialog.Builder(getContext())
                    .setTitle("Delete")
                    .setMessage("Are you sure you wish to delete this movie?")
                    .setPositiveButton(R.string.delete_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MovieSingleton mMovieSingleton = MovieSingleton.get(getContext());
                            mMovieSingleton.deleteMovie(mMovie);
                            Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                            updateUI();
                        }
                    })
                    .setNegativeButton(R.string.delete_no, null).show();

            return true;
        }
    }
    //End holder

}
























