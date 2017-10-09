package com.andnand.android.moivelist;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 9/7/17.
 */

public class MovieFetcher {

    private static final String LOG_TAG = "MoiveFetcher";

    private static final String API_KEY = "562bd33";

    public byte[] getURLBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " +
                                    urlSpec);

            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getURLBytes(urlSpec));
    }

    public List<Movie> fetchItems(String search) {

        List<Movie> items = new ArrayList<>();

        try {
            String url = Uri.parse("http://www.omdbapi.com/")
                    .buildUpon()
                    .appendQueryParameter("s", search)
                    .appendQueryParameter("apikey", API_KEY)
                    .build().toString();
            Log.i(LOG_TAG, "URL used: " + url);
            String jsonString = getUrlString(url);
            JSONObject jsonObject = new JSONObject(jsonString);
            parseItems(items, jsonObject);
            Log.i(LOG_TAG, "Received JSON: " + jsonString);
        } catch (JSONException exc) {
            Log.e(LOG_TAG, "Failed to parse JSON", exc);
        } catch (IOException exc) {
            Log.e(LOG_TAG, "Failed to fetch items", exc);

        }

        return items;
    }


    //TODO add ability to fetch full movie information
    public Movie fetchMovie(String imdbId) {

        Movie movie;

        try {
            String url = Uri.parse("http://www.omdbapi.com/")
                    .buildUpon()
                    .appendQueryParameter("i", imdbId)
                    .appendQueryParameter("apikey", API_KEY)
                    .build().toString();
            Log.i(LOG_TAG, "URL used: " + url);
            String jsonString = getUrlString(url);
            JSONObject jsonObject = new JSONObject(jsonString);
            movie = parseMovie(jsonObject);
            Log.i(LOG_TAG, "Received JSON: " + jsonString);
            return movie;
        } catch (JSONException exc) {
            Log.e(LOG_TAG, "Failed to parse JSON", exc);
        } catch (IOException exc) {
            Log.e(LOG_TAG, "Failed to fetch items", exc);

        }

        return null;
    }

    //TODO add ability to get next page
    //TODO switch to GSON
    private void parseItems(List<Movie> items, JSONObject jsonBody) throws IOException, JSONException {

        JSONArray photosJsonObject = jsonBody.getJSONArray("Search");


        for (int i = 0; i < photosJsonObject.length(); i++) {
            JSONObject photoJsonObject = photosJsonObject.getJSONObject(i);

            Movie movieItem = new Movie();
            movieItem.setTitle(photoJsonObject.getString("Title"));
            movieItem.setYear(photoJsonObject.getString("Year"));
            movieItem.setPoster(photoJsonObject.getString("Poster"));
            movieItem.setImdbId(photoJsonObject.getString("imdbID"));



            items.add(movieItem);
        }

    }

    private Movie parseMovie(JSONObject photoJsonObject) throws JSONException {

        Movie movieItem = new Movie();

        movieItem.setTitle(photoJsonObject.getString("Title"));
        movieItem.setYear(photoJsonObject.getString("Year"));
        movieItem.setPoster(photoJsonObject.getString("Poster"));
        movieItem.setImdbId(photoJsonObject.getString("imdbID"));
        movieItem.setRated(photoJsonObject.getString("Rated"));
        movieItem.setRuntime(photoJsonObject.getString("Runtime"));
        movieItem.setGenre(photoJsonObject.getString("Genre"));
        movieItem.setDirector(photoJsonObject.getString("Director"));
        movieItem.setWriter(photoJsonObject.getString("Writer"));
        movieItem.setActors(photoJsonObject.getString("Actors"));
        movieItem.setLanguage(photoJsonObject.getString("Language"));
        movieItem.setRatings(photoJsonObject.getString("Ratings"));
        if (photoJsonObject.isNull("Production"))
            movieItem.setProduction("N/A");
        else
            movieItem.setProduction(photoJsonObject.getString("Production"));
        if(photoJsonObject.isNull("Website"))
            movieItem.setWebsite("N/A");
        else
            movieItem.setWebsite(photoJsonObject.getString("Website"));

        Log.i(LOG_TAG, "movieItem title " + movieItem.getTitle());
        return movieItem;
    }

}



















