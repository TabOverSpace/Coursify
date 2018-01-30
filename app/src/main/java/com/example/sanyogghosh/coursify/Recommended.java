package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class Recommended extends Fragment {

    public Rec_Course_Adapter mAdapter;
    public ListView a;
    public View alone;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            alone = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.recommended_courses,container, false);




            ProgressBar pbar  = (ProgressBar)alone.findViewById(R.id.progress);
            pbar.setVisibility(View.VISIBLE);



            CourseMagic2 cm = new CourseMagic2();

            cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1Bgz2NvYRjpk3uE0SbCRLlPZQ_YKSncy8PMqJ82PuJ94/values/Sheet1!A2:F116?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");



            a = (ListView)alone.findViewById(R.id.list);
            mAdapter = new Rec_Course_Adapter(getActivity(), new ArrayList<Rec_Course_Holder>());
            a.setAdapter(mAdapter);
            a.setDivider(null);
            a.setScrollBarFadeDuration(0);



            return alone;
        }


    public final String LOG_TAG = "Not Working!";


    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(20000 /* milliseconds */);
            urlConnection.setConnectTimeout(30000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the course results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public List<Rec_Course_Holder> extractFeatureFromJson(String courseJSON) {
        // If the JSON string is empty or null, then return early.


        if (TextUtils.isEmpty(courseJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding courses to
        List<Rec_Course_Holder> courses = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(courseJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or courses).
            JSONArray courseArray = baseJsonResponse.getJSONArray("values");

            for (int i = 0; i < courseArray.length(); i++) {

                JSONArray currentcourse = courseArray.getJSONArray(i);

                Rec_Course_Holder ch = new Rec_Course_Holder(currentcourse.getString(0),currentcourse.getString(1),currentcourse.getString(2),currentcourse.getString(3),currentcourse.getString(4),currentcourse.getString(5));

                courses.add(ch);





            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Course JSON results", e);
        }

        // Return the list of courses
        return courses;
    }

    public List<Rec_Course_Holder> fetchCourseData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Rec_Course_Holder> courses = extractFeatureFromJson(jsonResponse);

        return courses;
    }


    public class CourseMagic2 extends AsyncTask<String, Void, List<Rec_Course_Holder>> {
        @Override
//#b75757 colour is the best for forecasts

        protected  List<Rec_Course_Holder> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Rec_Course_Holder> result = fetchCourseData(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(List<Rec_Course_Holder> data) {

            mAdapter.clear();



            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

                ProgressBar pbar  = (ProgressBar)alone.findViewById(R.id.progress);
                pbar.setVisibility(View.GONE);




            }
        }
        // Clear the adapter of previous earthquake data




    }
    int Y = 1;




    }



