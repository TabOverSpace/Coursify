package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Course_Adapter extends ArrayAdapter<Course_Holder> {

    public int N_ELEMENTS = 15;
    public int array[];
    public int draw[];
    public int img[];

    public Course_Adapter(Activity context, ArrayList<Course_Holder> androidFlavors) {

        super(context, 0, androidFlavors);
    }


    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view

        View listItemView = convertView;
        if(listItemView == null) {


            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.course_element, parent, false);
        }

        final Course_Holder currentAndroidFlavor = getItem(position);

        listItemView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.slidein));



        Typeface ag = Typeface.createFromAsset(getContext().getAssets(),"fonts/robotoslabbold.ttf");


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = currentAndroidFlavor.url;
                CustomTabsIntent.Builder customTabsBuilder = new CustomTabsIntent.Builder();
                customTabsBuilder.setToolbarColor(Color.parseColor("#48ba22"));
                customTabsBuilder.setShowTitle(true);
                customTabsBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.ic_done_black_24dp));
                customTabsBuilder.setStartAnimations(getContext(), R.anim.move3, R.anim.move_wow);
                customTabsBuilder.setExitAnimations(getContext(), R.anim.move3, R.anim.move_wow);
                CustomTabsIntent customTabsIntent = customTabsBuilder.build();
                customTabsIntent.launchUrl((Activity)getContext(), Uri.parse(url));
           }
        });

        array = new int[N_ELEMENTS];
        draw  = new int[N_ELEMENTS];
        img   = new int [N_ELEMENTS];

        for(int y=0;y<N_ELEMENTS;y++){

            array[y]=0;
            draw[y]=0;
            img[y]=0;

        }


        ImageView img1 = (ImageView)listItemView.findViewById(R.id.im1);
        ImageView img2 = (ImageView)listItemView.findViewById(R.id.im2);
        ImageView img3 = (ImageView)listItemView.findViewById(R.id.im3);


        CardView c1 = (CardView)listItemView.findViewById(R.id.c1);
        CardView c2 = (CardView)listItemView.findViewById(R.id.c2);
        CardView c3 = (CardView)listItemView.findViewById(R.id.c3);

        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);

        c1.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);
        c3.setVisibility(View.GONE);


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
                            c3.setVisibility(View.VISIBLE);
                            img3.setImageResource(draw[y]);
                            img3.setVisibility(View.VISIBLE);
                            img[3] = 1;
                        }


                    }
                    else {
                        c2.setVisibility(View.VISIBLE);
                        img2.setImageResource(draw[y]);
                        img2.setVisibility(View.VISIBLE);
                        img[1] = 1;
                    }
                }
                else {
                    c1.setVisibility(View.VISIBLE);
                    img1.setImageResource(draw[y]);
                    img1.setVisibility(View.VISIBLE);
                    img[0] = 1;
                }

            }

        }

        TextView a  = (TextView)listItemView.findViewById(R.id.course);
        a.setText(currentAndroidFlavor.course);
        a.setTypeface(ag);

        TextView b  = (TextView)listItemView.findViewById(R.id.provider);
        b.setText(currentAndroidFlavor.provider);

        ImageView c  = (ImageView) listItemView.findViewById(R.id.site);
        ImageView d  = (ImageView) listItemView.findViewById(R.id.sited);



        Glide.with(getContext())
                .load(currentAndroidFlavor.img_url)
                .centerCrop()
                .placeholder(R.drawable.book_blue)
                .crossFade()
                .into(c);

        d.setImageResource(R.drawable.edx);

        if(currentAndroidFlavor.site.equals("edx")){

            d.setImageResource(R.drawable.edx);

        }

        if(currentAndroidFlavor.site.equals("Udemy")){

            d.setImageResource(R.drawable.udemy);

        }
        if(currentAndroidFlavor.site.equals("Udacity")){

            d.setImageResource(R.drawable.udacityu);

        }
        if(currentAndroidFlavor.site.equals("Coursera")){

            d.setImageResource(R.drawable.courserag);

        }


        TextView e  = (TextView)listItemView.findViewById(R.id.difficulty);
        if (currentAndroidFlavor.level.equals("0")){
            e.setText("");
        }
        else {
            e.setText(currentAndroidFlavor.level);
        }



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

        if(contains.contains("creative")||contains.contains("think")||contains.contains("mind")){

            array[5] = 1;
            draw[5] = R.drawable.creative;



        }

        if(contains.contains("python")){

            array[6] = 1;
            draw[6] = R.drawable.py;



        }
        if(contains.contains("communication")||contains.contains("talk")){

            array[7] = 1;
            draw[7] = R.drawable.comm;



        }

        if(contains.contains("music")){

            array[8] = 1;
            draw[8] = R.drawable.music;



        }
        if(contains.contains("data")&&contains.contains("science")){

            array[9] = 1;
            draw[9] = R.drawable.molecule;



        }
        if(contains.contains("money")&&contains.contains("finance")||contains.contains("business")){

            array[10] = 1;
            draw[10] = R.drawable.finance;



        }
        if(contains.contains("earth")||contains.contains("geographic")||contains.contains("geography")){

            array[11] = 1;
            draw[11] = R.drawable.earth;



        }
        if(contains.contains("data")){

            array[12] = 1;
            draw[12] = R.drawable.folder;



        }
        if(contains.contains("weight")){

            array[13] = 1;
            draw[13] = R.drawable.weight;



        }
        if(contains.contains("computer")&&contains.contains("science")){

            array[14] = 1;
            draw[14] = R.drawable.data_science;



        }

    }



}

