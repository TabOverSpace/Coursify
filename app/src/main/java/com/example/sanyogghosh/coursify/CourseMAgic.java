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

public class CourseMagic {

    private String LOG_TAG = "Failed!";
    private int c1;
    private int c2;
    private int c3;
    private int c4;
    private String query="";
    private String level2="";


    public void setValues(int c1, int c2, int c3, int c4, String query, String level2){
        this.c1=c1;
        this.c2=c2;
        this.c3=c3;
        this.c4=c4;
        this.query=query;
        this.level2=level2;
    }


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
    public List<Course_Holder> extractFeatureFromJson(String courseJSON) {
        // If the JSON string is empty or null, then return early.

        if (TextUtils.isEmpty(courseJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding courses to
        List<Course_Holder> courses = new ArrayList<>();

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
                Course_Holder ch = new Course_Holder(currentcourse.getString(0),currentcourse.getString(1),currentcourse.getString(5),currentcourse.getString(4),currentcourse.getString(3),currentcourse.getString(2),currentcourse.getString(6));
                Log.d("WOAH  ",""+currentcourse.getString(0));

                level(currentcourse.getString(4),courses,ch,currentcourse);



            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Course JSON results", e);
        }

        // Return the list of courses
        return courses;
    }
    public List<Course_Holder> fetchCourseData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Course_Holder> courses = extractFeatureFromJson(jsonResponse);

        return courses;
    }
    public void Query(CharSequence query,JSONArray currentcourse ,List<Course_Holder> courses ,Course_Holder ch){
        if(!query.equals("")){


            try {
                if(currentcourse.getString(0).toLowerCase().contains(query)){
                    courses.add(ch);
                }
                else{
                    try {
                        if(currentcourse.getString(1).toLowerCase().contains(query)){

                            courses.add(ch);

                        }

                        else{

                            try {
                                if(currentcourse.getString(3).toLowerCase().contains(query)){


                                    courses.add(ch);


                                }

                                else{

                                    if(currentcourse.getString(4).toLowerCase().contains(query)){

                                        courses.add(ch);


                                    }
                                    else{

                                        if(currentcourse.getString(5).toLowerCase().contains(query)){

                                            courses.add(ch);


                                        }

                                    }


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(query.equals("")){


            courses.add(ch);

        }
    }
    public void level(String level,List<Course_Holder> courses,Course_Holder ch,JSONArray currentcourses){
        if(c1==1){

            try {
                if(currentcourses.getString(5).equals("Udacity")){

                    if(level2.equals("All Levels")){

                        Query(query, currentcourses, courses, ch);

                    }
                    else {
                        if (level.equals(level2)) {
                            Query(query, currentcourses, courses, ch);


                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        if(c2==1){

            try {
                if(currentcourses.getString(5).equals("edx")){

                    if(level2.equals("All Levels")){

                        Query(query, currentcourses, courses, ch);

                    }
                    else {
                        if (level.equals(level2)) {
                            Query(query, currentcourses, courses, ch);


                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c3==1){

            try {
                if(currentcourses.getString(5).equals("Udemy")){

                    if(level2.equals("All Levels")){

                        Query(query, currentcourses, courses, ch);

                    }
                    else {
                        if (level.equals(level2)) {
                            Query(query, currentcourses, courses, ch);


                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(c4==1){

            try {
                if(currentcourses.getString(5).equals("Coursera")){

                    if(level2.equals("All Levels")){

                        Query(query, currentcourses, courses, ch);

                    }
                    else {
                        if (level.equals(level2)) {
                            Query(query, currentcourses, courses, ch);


                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}