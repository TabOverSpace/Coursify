package com.example.sanyogghosh.coursify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Fourth extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ListView a;
    public CharSequence query;
    public int get2,c1,c2,c3,c4 = 0;
    public String level2="";
    public String value="";


    private Course_Adapter mAdapter;
    private ArrayList<Course_Holder> arraylist;

    public void filter(CharSequence charText) {
        charText = charText.toString().toLowerCase(Locale.getDefault());
        mAdapter.clear();
        if (charText.length() == 0) {
            mAdapter.addAll(arraylist);
        } else {
            for (Course_Holder wp : arraylist) {
                if (wp.course().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    mAdapter.add(wp);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView d  = (ImageView)findViewById(R.id.search);
        d.setVisibility(View.VISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        a = (ListView)findViewById(R.id.list);
        mAdapter = new Course_Adapter(this, new ArrayList<Course_Holder>());
        a.setAdapter(mAdapter);
        a.setDivider(null);

        try {
            FileInputStream fileIn = openFileInput("secondorthird.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            int charRead;
            value="";
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                final String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                value+= readstring;
            }
            InputRead.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        query="";


        //execute course magic
        CourseMagic cm = new CourseMagic();
        //the api of sheets


        //get pos no. from previous activity
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);




        get2 = pref.getInt("position_number",0);

        c1 = pref.getInt("c1",0);
        c2 = pref.getInt("c2",0);
        c3 = pref.getInt("c3",0);
        c4 = pref.getInt("c4",0);




        level2 = pref.getString("level","ho");






        //done pos\

        value(cm);

            //PROGRESS

        pvisible();

        EditText fg = (EditText)findViewById(R.id.filter);

        if (fg != null) {
            fg.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text

                    query  =  cs.toString().toLowerCase();

                    CourseMagic cm = new CourseMagic();
                    //the api of sheets

                    if(value.equals("0")) {
                        switch (get2) {

                            case 5:

                                cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1-5ErS-ilnGz7c282lUwQs803dXqDNyz1xT4EmfluXFo/values/Sheet1!A2:E153?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                                break;
                            case 6:

                                cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1rtuFGn-D6H2OF7cQzPCjihKxPYeNUVoY6YV_XaPrpos/values/Sheet1!A2:E153?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                                break;

                            case 7:

                                cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1UeD3FhBq_4Inxvb2k0bNPeXPBozfUDqhToAi7bFtTm4/values/Sheet1!A2:E116?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                                break;

                        }
                    }
                    if(value.equals("1")) {
                        switch (get2) {

                            case 7:

                                cm.execute("https://sheets.googleapis.com/v4/spreadsheets/19rGoN5Ks_7pwLptQDkBWXA4v6G6FVQDWxYxISKPmOmk/values/Sheet1!A2:E116?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                                break;

                        }
                    }


                    ImageView d  = (ImageView)findViewById(R.id.search);
                    d.setVisibility(View.GONE);

                    ProgressBar df = (ProgressBar)findViewById(R.id.progress2);
                    df.setVisibility(View.VISIBLE);


                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fourth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    //Below code important
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

                Course_Holder ch = new Course_Holder(currentcourse.getString(2),currentcourse.getString(0),currentcourse.getString(1),currentcourse.getString(3),currentcourse.getString(4));


                level(currentcourse.getString(3),courses,ch,currentcourse);


                
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


    public class CourseMagic extends AsyncTask<String, Void, List<Course_Holder>> {
        @Override
//#b75757 colour is the best for forecasts

        protected  List<Course_Holder> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Course_Holder> result = fetchCourseData(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(List<Course_Holder> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);

                pgone();


                ImageView d  = (ImageView)findViewById(R.id.search);
                d.setVisibility(View.VISIBLE);

                ProgressBar df = (ProgressBar)findViewById(R.id.progress2);
                df.setVisibility(View.GONE);


            }
        }
        // Clear the adapter of previous earthquake data




    }
    int Y = 1;
    public void vanish(View v){
        if(Y%2==1){

            LinearLayout f  = (LinearLayout)findViewById(R.id.justvanish);
            f.setVisibility(View.VISIBLE);
            f.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_down));
            ImageView g = (ImageView)findViewById(R.id.search2);
            g.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out));
            g.setImageResource(R.drawable.cancel);
            g.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));


        }

        if(Y%2==0) {

            LinearLayout f  = (LinearLayout)findViewById(R.id.justvanish);
            f.setVisibility(View.GONE);
            f.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_up));
            ImageView g = (ImageView)findViewById(R.id.search2);
            g.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out));
            g.setImageResource(R.drawable.search);
            g.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_in));


        }
        Y++;

    }


    public void back(View v){

        Intent i = new Intent(this,First2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        finish();


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void value(CourseMagic cm){
        TextView a = (TextView)findViewById(R.id.what);

        if(value.equals("0")) {
            switch (get2) {

                case 5:
                    a.setText("Programming Languages");
                    cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1-5ErS-ilnGz7c282lUwQs803dXqDNyz1xT4EmfluXFo/values/Sheet1!A2:E153?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                    break;
                case 6:
                    a.setText("Web Development");
                    cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1rtuFGn-D6H2OF7cQzPCjihKxPYeNUVoY6YV_XaPrpos/values/Sheet1!A2:E153?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                    break;

                case 7:
                    a.setText("App Development");
                    cm.execute("https://sheets.googleapis.com/v4/spreadsheets/1UeD3FhBq_4Inxvb2k0bNPeXPBozfUDqhToAi7bFtTm4/values/Sheet1!A2:E116?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                    break;

            }
        }
        if(value.equals("1")) {
            switch (get2) {

                case 7:
                    a.setText("Philosophy");
                    cm.execute("https://sheets.googleapis.com/v4/spreadsheets/19rGoN5Ks_7pwLptQDkBWXA4v6G6FVQDWxYxISKPmOmk/values/Sheet1!A2:E116?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8");
                    break;

            }
        }


    }

    public void pvisible (){

        ProgressBar pbar  = (ProgressBar)findViewById(R.id.progress);
        pbar.setVisibility(View.VISIBLE);

    }

    public void pgone (){

        ProgressBar pbar  = (ProgressBar)findViewById(R.id.progress);
        pbar.setVisibility(View.GONE);

    }
    public void Query(CharSequence query,JSONArray currentcourse ,List<Course_Holder> courses ,Course_Holder ch){
        if(!query.equals("")){


            try {
                if(currentcourse.getString(2).toLowerCase().contains(query)){
                courses.add(ch);
                }
                else{
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

                                    if(currentcourse.getString(3).toLowerCase().contains(query)){

                                        courses.add(ch);


                                    }
                                    else{

                                        if(currentcourse.getString(4).toLowerCase().contains(query)){

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
                if(currentcourses.getString(1).equals("Udacity")){

                    if(level2.equals("All Levels")){

                        courses.add(ch);

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
                if(currentcourses.getString(1).equals("edx")){

                    if(level2.equals("All Levels")){

                        courses.add(ch);

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
                if(currentcourses.getString(1).equals("Udemy")){

                    if(level2.equals("All Levels")){

                        courses.add(ch);

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
                if(currentcourses.getString(1).equals("Coursera")){

                    if(level2.equals("All Levels")){

                        courses.add(ch);

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
