package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Rec_Course_Adapter extends ArrayAdapter<Rec_Course_Holder> {

    private  final String LOG_TAG = Rec_Course_Adapter.class.getSimpleName();


    public Rec_Course_Adapter(Activity context, ArrayList<Rec_Course_Holder> androidFlavors) {

        super(context, 0, androidFlavors);
    }
    public int N_ELEMENTS = 5;
    public int array[];
    public int draw[];
    public int img[];

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        array = new int[N_ELEMENTS];
        draw  = new int[N_ELEMENTS];
        img   = new int [N_ELEMENTS];

        for(int y=0;y<4;y++){

            array[y]=0;
            draw[y]=0;
            img[y]=0;

        }

        View listItemView = convertView;
        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.recommended_element, parent, false);
        }

        final Rec_Course_Holder currentAndroidFlavor = getItem(position);



        Typeface ag = Typeface.createFromAsset(getContext().getAssets(),"fonts/robotoslabbold.ttf");


        listItemView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_in));




        TextView a  = (TextView)listItemView.findViewById(R.id.course);
        a.setText(currentAndroidFlavor.course);
        a.setTypeface(ag);

        ImageView img1 = (ImageView)listItemView.findViewById(R.id.img1);
        ImageView img2 = (ImageView)listItemView.findViewById(R.id.img2);
        ImageView img3 = (ImageView)listItemView.findViewById(R.id.img3);
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);

        String df = currentAndroidFlavor.course.toLowerCase();
        contains(df);

        for(int y = 0;y<N_ELEMENTS;y++){
            Log.d("aa",""+draw[y]);

            if(draw[y]!=0){
                if(img[0]!=0){
                    if(img[1]!=0){
                        if(img[2]!=0){

                            break;
                        }
                        else {
                            img3.setImageResource(draw[y]);
                            img3.setVisibility(View.VISIBLE);
                            img[3] = 1;
                        }


                    }
                    else {
                        img2.setImageResource(draw[y]);
                        img2.setVisibility(View.VISIBLE);
                        img[1] = 1;
                    }
                }
                else {
                    img1.setImageResource(draw[y]);
                    img1.setVisibility(View.VISIBLE);
                    img[0] = 1;
                }

            }

        }



        TextView b  = (TextView)listItemView.findViewById(R.id.course_info);
        b.setText(currentAndroidFlavor.info);


        TextView ab  = (TextView)listItemView.findViewById(R.id.price);
        ab.setText(currentAndroidFlavor.price);
//data set
        Uri art = Uri.parse(currentAndroidFlavor.link);
        final Intent f = new Intent(Intent.ACTION_VIEW);
        f.setData(art);

        FrameLayout fgg = (FrameLayout) listItemView.findViewById(R.id.link);
        fgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getContext().startActivity(f);

            }
        });




        FrameLayout fgf = (FrameLayout) listItemView.findViewById(R.id.share);
        fgf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent gh = new Intent();
                    gh.setAction(Intent.ACTION_SEND);
                    gh.setType("text/plain");
                    gh.putExtra(Intent.EXTRA_TEXT,"I found this course quite interesting! \n\n"+currentAndroidFlavor.course+"\n\n"+Uri.parse(currentAndroidFlavor.link));
                    getContext().startActivity(gh);
                }
                catch (Exception e){

                    Log.d("Error","error");
                }
            }
        });




        ImageView c  = (ImageView) listItemView.findViewById(R.id.site);

        c.setImageResource(R.drawable.edx);


        if(currentAndroidFlavor.site.equals("edx")){

            c.setImageResource(R.drawable.edx);

        }

        if(currentAndroidFlavor.site.equals("Udemy")){

            c.setImageResource(R.drawable.udemy);

        }
        if(currentAndroidFlavor.site.equals("Udacity")){

            c.setImageResource(R.drawable.udacityu);

        }
        if(currentAndroidFlavor.site.equals("Coursera")){


        }


        TextView d  = (TextView)listItemView.findViewById(R.id.rating);
        d.setText(""+currentAndroidFlavor.rating);




        return listItemView;
    }



    public void contains(String contains){
        if(contains.contains("java")){

            array[0] = 1;
            draw[0] = R.drawable.java;

        }


        if(contains.contains("web")){

            array[1] = 1;
            draw[1] = R.drawable.programming;


        }
        if(contains.contains("ios")){

            array[2] = 1;
            draw[2] = R.drawable.ios;

        }

        if(contains.contains("android")){

            array[3] = 1;
            draw[3] = R.drawable.android;



        }
        if(contains.contains("machine")){

            array[4] = 1;
            draw[4] = R.drawable.chip;



        }


    }


}


