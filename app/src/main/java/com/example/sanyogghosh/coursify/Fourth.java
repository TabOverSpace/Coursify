package com.example.sanyogghosh.coursify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fourth extends AppCompatActivity {

    public ListView ab;
    public CharSequence query;
    public int get2,c1,c2,c3,c4 = 0;
    public String level2="";
    public String value="";
    public ArrayList<Link_Holder> myList;
    private Course_Adapter mAdapter;
    private LinkMagically a = new LinkMagically();
    private TextView sear;
    private TextView sear2;
    private CardView sear2card;
    private TextView search_none;
    private ProgressBar pbar;

    private int y, z = 0;
      // Code to execute to perform this operation

    private String LINKS_LINK ="https://sheets.googleapis.com/v4/spreadsheets/1v74t8pbPtJKcLdViY4eM77gwCEj8yrGxvDxZ4HI8zfQ/values/Sheet1!A2:E16?key=AIzaSyBa8lxt2dSjV5aw9RkU0uh38h6jYCI9mm8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView d  = (ImageView)findViewById(R.id.search);
        d.setVisibility(View.VISIBLE);
        pbar = (ProgressBar)findViewById(R.id.progress);

        sear = (TextView) findViewById(R.id.sear);
        sear2 = (TextView)findViewById(R.id.sear2);
        search_none = (TextView)findViewById(R.id.select_none);
        //upper case below!
        sear2card = (CardView)findViewById(R.id.sear2_card);
        ab = (ListView)findViewById(R.id.list);
        mAdapter = new Course_Adapter(this, new ArrayList<Course_Holder>());
        ab.setAdapter(mAdapter);
        ab.setDivider(null);
        value_finder();

        query="";
        myList = new ArrayList<>();

        //execute course magic
        //the api of sheets
        ab.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if(mLastFirstVisibleItem<firstVisibleItem)
                {
                    z=0;
                    if(y==0) {
                        CardView options = (CardView) findViewById(R.id.sear2_card);
                        Animation a = AnimationUtils.loadAnimation(Fourth.this, R.anim.abc_slide_out_bottom);
                        options.startAnimation(a);
                    }if(y>0) {
                    CardView options = (CardView) findViewById(R.id.sear2_card);
                    options.setVisibility(View.GONE);
                }Log.i("SCROLLING DOWN","TRUE");
                    y++;
                }
                if(mLastFirstVisibleItem>firstVisibleItem)
                {  y=0;
                    if(z==0) {
                        CardView options = (CardView) findViewById(R.id.sear2_card);
                        options.setVisibility(View.VISIBLE);
                        Animation a = AnimationUtils.loadAnimation(Fourth.this, R.anim.abc_slide_in_top);
                        options.startAnimation(a);
                    }
                    z++;
                }

                mLastFirstVisibleItem=firstVisibleItem;

            }
        });



        //get pos no. from previous activity
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Mypref", MODE_PRIVATE);

        get2 = pref.getInt("position_number",0);

        c1 = pref.getInt("c1",0);
        c2 = pref.getInt("c2",0);
        c3 = pref.getInt("c3",0);
        c4 = pref.getInt("c4",0);
        if(c1==0&&c2==0&&c3==0&&c4==0){
            pbar.setVisibility(View.GONE);
            search_none.setVisibility(View.VISIBLE);
        }

        level2 = pref.getString("level","ho");
        if(level2.equals("Beginner")){
            level2 = "Introductory";
        }
        if(level2.equals("0")){
            level2 = "All Levels";
        }

        a.execute(LINKS_LINK);

            //PROGRESS

        pvisible();
        sear.setVisibility(View.GONE);
        EditText fg = (EditText)findViewById(R.id.filter);



        if (fg != null) {
            /*
            fg.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    mAdapter.clear();
                    query  =  cs.toString().toLowerCase();
                    Log.d("query",query.toString());
                    CourseMagically cm = new CourseMagically();
                    //below lies the value
                    value();
                    //the api of sheets



                    ImageView d  = (ImageView)findViewById(R.id.search);
                    d.setVisibility(View.GONE);


                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    // TODO Auto-generated method stub

                    ProgressBar df = (ProgressBar)findViewById(R.id.progress2);
                    df.setVisibility(View.VISIBLE);

                }

                @Override
                public void afterTextChanged(Editable arg0) {
                    // TODO Auto-generated method stub
                }
            });
        */
        }

    }
    //Below code important
    public final String LOG_TAG = "Not Working!";

    //async task for receiving links
    public class CourseMagically extends AsyncTask<String, Void, List<Course_Holder>> {
        @Override
        protected  List<Course_Holder> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            CourseMagic a = new CourseMagic();
            a.setValues(c1,c2,c3,c4,(String)query,level2);
            List<Course_Holder> result = a.fetchCourseData(urls[0]);
            return result;
        }
        @Override
        protected void onPostExecute(List<Course_Holder> data) {

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
                pgone();
                ImageView d  = (ImageView)findViewById(R.id.search);
                d.setVisibility(View.VISIBLE);
                sear2.setText(mAdapter.getCount()+" courses found..");
                sear2.setVisibility(View.VISIBLE);
                sear2card.setVisibility(View.VISIBLE);
                ProgressBar df = (ProgressBar)findViewById(R.id.progress2);
                df.setVisibility(View.GONE);
            }
            else {
                if(mAdapter.isEmpty()) {
                    pgone();
                    sear.setVisibility(View.VISIBLE);
                }
            }
        }
   }
    //async task for receiving courses from diff sites
    public class LinkMagically extends AsyncTask<String, Void, List<Link_Holder>> {
        @Override
//#b75757 colour is the best for forecasts

        protected  List<Link_Holder> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            LinkMagic a = new LinkMagic();
            List<Link_Holder> result = a.fetchCourseData2(urls[0]);

            return result;

        }

        @Override
        protected void onPostExecute(List<Link_Holder> data) {
            if (data != null && !data.isEmpty()) {
                myList.addAll(data);
                value();
            }
        }
        // Clear the adapter of previous earthquake data




    }
    //recursive Y
    int Y = 1;

    //below method is for search box
    public void vanish(View v){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_search, null);
        final EditText input  = (EditText)dialogView.findViewById(R.id.edit1);
        alertDialog.setView(dialogView);
        alertDialog.setPositiveButton("SEARCH", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mAdapter.clear();
                query = input.getText().toString();
                pvisible();
                value();
            }
        });
        alertDialog.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
        /*
        if(Y%2==1){

            LinearLayout f  = (LinearLayout)findViewById(R.id.justvanish);
            f.setVisibility(View.VISIBLE);
            f.startAnimation(AnimationUtils.loadAnimation(this,R.anim.slide_down));
            ImageView g = (ImageView)findViewById(R.id.search2);
            g.startAnimation(AnimationUtils.loadAnimation(this,R.anim.fade_out));
            g.setImageResource(R.drawable.error);
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
*/
    }
    //below method is for back button
    public void back(View v){

        Intent i = new Intent(this,First2.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        overridePendingTransition(R.anim.move3,R.anim.move_wow);
        finish();


    }

    //below method is for setting heading and even exceuting tasks
    public void value(){
        TextView a = (TextView)findViewById(R.id.what);

        if(value.equals("0")) {
            switch (get2) {

                case 0:
                    a.setText("Information Technology");
                    forLoop("Information Technology",myList);
                    break;

                case 1:
                    a.setText("Data Science");
                    forLoop("Data Science",myList);
                    break;

                case 2:
                    a.setText("Computer Science");
                    forLoop("Computer Science",myList);
                    break;

                case 3:
                    a.setText("Math and Logic");
                    forLoop("Math and Logic",myList);
                    break;


                case 4:
                    a.setText("Programming Languages");
                    forLoop("Programming Languages",myList);
                    break;

                case 5:
                    a.setText("Web Development");
                    forLoop("Web Development",myList);
                    break;

                case 6:
                    a.setText("App Development");
                    forLoop("App Development",myList);
                    break;

            }
        }
        if(value.equals("1")) {
            switch (get2) {
                case 0:
                    a.setText("Business");
                    forLoop("Business",myList);
                    break;
                case 1:
                    a.setText("Life Sciences");
                    forLoop("Life Sciences",myList);
                    break;
                case 2:
                    a.setText("Arts and Humanities");
                    forLoop("Arts and Humanities",myList);
                    break;
                case 3:
                    a.setText("Personal Development");
                    forLoop("Personal Development",myList);
                    break;

                case 4:
                    a.setText("Social Sciences");
                    forLoop("Social Sciences",myList);
                    break;
                case 5:
                    a.setText("Language Learning");
                    forLoop("Language Learning",myList);
                    break;

                case 6:
                    a.setText("Philosophy and Ethics");
                    forLoop("Philosophy",myList);
                    break;

                case 7:
                    a.setText("Physical Science and Engineering");
                    forLoop("Physical Science and Engineering",myList);
                    break;

            }
        }

    }

    //progress bar visible
    public void pvisible (){
        pbar.setVisibility(View.VISIBLE);
        sear2.setVisibility(View.GONE);
        sear2card.setVisibility(View.GONE);
        sear.setVisibility(View.GONE);

    }
    //progress bar gone
    public void pgone (){
        pbar.setVisibility(View.GONE);
        sear2.setVisibility(View.GONE);
        sear2card.setVisibility(View.GONE);
        sear.setVisibility(View.GONE);
    }

    //below method is for animations
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    //below method is for receiving 0,1 values ->whther he tapped on first section or second section
    public void value_finder(){
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
    }

    //below method is used in value method
    public void forLoop(String h, ArrayList<Link_Holder> a){
        CourseMagically ab = new CourseMagically();
        CourseMagically b = new CourseMagically();
        CourseMagically c = new CourseMagically();
        CourseMagically d = new CourseMagically();


        int length = a.size();
        Log.d("JOOO",""+a);
        String returnText="";
        int num =0;
        for(int i=0; i<length;i++){
            if(a.get(i).getSite().equals(h)) {

                returnText = h;
                num = i;

            }
        }

        if(c1==1){
            ab.execute(a.get(num).getUdacity());
            Log.d("Udacity",a.get(num).getUdacity());
        }
        if(c2 == 1){
            b.execute(a.get(num).getEdX());
            Log.d("edx",a.get(num).getEdX());
        }
        if(c3 == 1){
            c.execute(a.get(num).getUdemy());
            Log.d("udemy",a.get(num).getUdemy());
        }
        if(c4 == 1){
            d.execute(a.get(num).getCoursera());
            Log.d("coursera",a.get(num).getCoursera());
        }
    }

}
