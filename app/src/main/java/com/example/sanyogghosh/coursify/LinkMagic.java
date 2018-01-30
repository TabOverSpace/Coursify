package com.example.sanyogghosh.coursify;


import android.text.TextUtils;
import android.util.Log;

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

public class LinkMagic{
    private String LOG_TAG = "Failed!";

    private URL createUrl2(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }
    private String makeHttpRequest2(URL url) throws IOException {
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
                jsonResponse = readFromStream2(inputStream);
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
    private String readFromStream2(InputStream inputStream) throws IOException {
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
    public List<Link_Holder> extractFeatureFromJson2(String courseJSON) {
        // If the JSON string is empty or null, then return early.

        if (TextUtils.isEmpty(courseJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding courses to
        List<Link_Holder> courses = new ArrayList<>();

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
                //course                        //provider                 //site                  //level                     //url                      //url_fi
                Link_Holder ch = new Link_Holder(currentcourse.getString(0),currentcourse.getString(1),currentcourse.getString(2),currentcourse.getString(3),currentcourse.getString(4));
                courses.add(ch);


            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Course JSON results", e);
        }

        // Return the list of courses
        return courses;
    }
    public List<Link_Holder> fetchCourseData2(String requestUrl) {

        URL url = createUrl2(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest2(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Link_Holder> courses = extractFeatureFromJson2(jsonResponse);

        return courses;
    }

}