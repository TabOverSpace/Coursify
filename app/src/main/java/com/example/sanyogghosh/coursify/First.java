package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class First extends Fragment {
        Spinner a,b,c;
        int A,B,C;
        int F,F1,G,G1,H,H1,J,J1;
        int agar;
        Runnable m;
        int conditions[];
        private InterstitialAd adView;  // The ad
        private AdRequest adRequest;
        private Handler mHandler;       // Handler to display the ad on the UI thread
        private Runnable displayAd;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final View alone = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_first,container, false);



            a  = (Spinner)alone.findViewById(R.id.spinne);

            b =  (Spinner)alone.findViewById(R.id.spinne2);
            B = b.getSelectedItemPosition();
            c =  (Spinner)alone.findViewById(R.id.spinne3);
            C = b.getSelectedItemPosition();

            agar = 0;
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                    R.array.course_array1, R.layout.special_dropdown_view);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.special_dropdown);
// Apply the adapter to the spinner
            conditions  = new int [4];

            a.setAdapter(adapter);

            final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.course_array2, R.layout.special_dropdown_view);
            b.setAdapter(adapter2);
            adapter2.setDropDownViewResource(R.layout.special_dropdown);

            final ArrayAdapter<CharSequence> adapter23 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.course_array3, R.layout.special_dropdown_view);
            adapter23.setDropDownViewResource(R.layout.special_dropdown);



            try {
                FileOutputStream fileout = getContext().openFileOutput("secondorthird.txt", 1);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write("0");
                outputWriter.close();


            } catch (Exception e) {
                e.printStackTrace();
            }


            final Handler mhandler = new Handler();

            m = new Runnable() {
                @Override
                public void run() {
                    a  = (Spinner)alone.findViewById(R.id.spinne);
                    b =  (Spinner)alone.findViewById(R.id.spinne2);


                    if(a.getSelectedItemPosition()==1){


                    }

                    A = a.getSelectedItemPosition();
                    if(A==0){

                        if(agar!=0){

                            b.setAdapter(adapter2);

                            try {
                                FileOutputStream fileout = getContext().openFileOutput("secondorthird.txt", 1);
                                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                                outputWriter.write("0");
                                outputWriter.close();


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        agar=0;
                    }
                    if (A==1){
                        if(agar!=1){

                            b.setAdapter(adapter23);

                            try {
                                FileOutputStream fileout = getContext().openFileOutput("secondorthird.txt", 1);
                                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                                outputWriter.write("1");
                                outputWriter.close();


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        agar = 1;


                    }

                    C = b.getSelectedItemPosition();

                    SharedPreferences pref = getContext().getSharedPreferences("Mypref", 1);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("position_number", C);
                    editor.apply();


                    mhandler.postDelayed(m,1000);
                }
            };

            mhandler.postDelayed(m,1000);





// Specify the layout to use when the list of choices appears
            adapter2.setDropDownViewResource(R.layout.special_dropdown);
// Apply the adapter to the spinner



            ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getActivity(),
                    R.array.course_array4, R.layout.special_dropdown_view);
// Specify the layout to use when the list of choices appears
            adapter3.setDropDownViewResource(R.layout.special_dropdown);
// Apply the adapter to the spinner
            c.setAdapter(adapter3);

            fonts_set(alone);

            if(A==0){
                b.setAdapter(adapter2);
            }

            if (A==1){
                b.setAdapter(adapter23);
            }

            b =  (Spinner)alone.findViewById(R.id.spinne2);
            B = b.getSelectedItemPosition();

            c =  (Spinner)alone.findViewById(R.id.spinne3);
            C = b.getSelectedItemPosition();

        FrameLayout fab = (FrameLayout) alone. findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref = getContext().getSharedPreferences("Mypref", 1);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("c1", conditions[0]);
                editor.putInt("c2", conditions[1]);
                editor.putInt("c3", conditions[2]);
                editor.putInt("c4", conditions[3]);

                if(c.getSelectedItemPosition()==0){

                    editor.putString("level", "All Levels");
                }

                if(c.getSelectedItemPosition()==1){

                    editor.putString("level", "Introductory");
                }


                if(c.getSelectedItemPosition()==2){

                    editor.putString("level", "Intermediate");
                }


                if(c.getSelectedItemPosition()==3){

                    editor.putString("level", "Advanced");
                }
                editor.apply();
                adRequest = new AdRequest.Builder().build();
                adView = new InterstitialAd(getContext());
                //ca-app-pub-1887633011875798~3464801599
                MobileAds.initialize(getContext(), "ca-app-pub-1887633011875798~3464801599");
                adView.setAdUnitId("ca-app-pub-1887633011875798/5999341880");
                mHandler = new Handler(Looper.getMainLooper());
                displayAd = new Runnable() {
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if (adView.isLoaded()) {
                                    adView.show();
                                }
                            }
                        });
                    }
                };
                loadAd();
                displayInterstitial();
                Intent o = new Intent(getActivity().getApplicationContext(),Fourth.class);
                startActivity(o);
                getActivity().overridePendingTransition(R.anim.move3,R.anim.move_wow);
            }
        });

            final ImageView f1 = (ImageView)alone.findViewById(R.id.uda);
            f1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean a = (F%2 == 0) ? (false) : (true);
                    double alpha = (a == true) ? (0.4) : (1);
                    int  status = (alpha==0.4) ? (0) : (1) ;
                    conditions[0] = status;
                    f1.setAlpha((float)alpha);
                    f1.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in2));
                    F++;
                }
            });

            final ImageView f2 = (ImageView)alone.findViewById(R.id.edxa);

            f2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean a = (G%2 == 0) ? (false) : (true);
                    double alpha = (a == true) ? (0.4) : (1);
                    int  status = (alpha==0.4) ? (0) : (1) ;
                    conditions[1] = status;
                    f2.setAlpha((float)alpha);
                    f2.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in2));
                    G++;
                }
            });

            final ImageView f3 = (ImageView)alone.findViewById(R.id.udemy);
            f3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean a = (H%2 == 0) ? (false) : (true);
                    double alpha = (a == true) ? (0.4) : (1);
                    int  status = (alpha==0.4) ? (0) : (1) ;
                    conditions[2] = status;
                    f3.setAlpha((float)alpha);
                    f3.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in2));
                    H++;
                }
            });

            final ImageView f4 = (ImageView)alone.findViewById(R.id.cours);


            f4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean a = (J%2 == 0) ? (false) : (true);
                    double alpha = (a == true) ? (0.4) : (1);
                    int  status = (alpha==0.4) ? (0) : (1) ;
                    conditions[3] = status;
                    f4.setAlpha((float)alpha);
                    f4.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in2));
                    J++;
                }
            });
     return alone;
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

    public void fonts_set(View alone){


            Typeface ag = Typeface.createFromAsset(getContext().getAssets(),"fonts/robotoslabbold.ttf");

            TextView f = (TextView)alone.findViewById(R.id.c_type);

            f.setTypeface(ag);

            TextView f1 = (TextView)alone.findViewById(R.id.s_type);

            f1.setTypeface(ag);

            TextView f2 = (TextView)alone.findViewById(R.id.d_type);

            f2.setTypeface(ag);

        }

    void loadAd() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Load the adView object witht he request
        adView.loadAd(adRequest);
    }
    public void displayInterstitial() {
        mHandler.postDelayed(displayAd, 2000);
    }
}
