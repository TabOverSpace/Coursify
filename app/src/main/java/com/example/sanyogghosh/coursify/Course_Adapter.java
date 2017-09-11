package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Course_Adapter extends ArrayAdapter<Course_Holder> {

    private  final String LOG_TAG = Course_Adapter.class.getSimpleName();


    public Course_Adapter(Activity context, ArrayList<Course_Holder> androidFlavors) {

        super(context, 0, androidFlavors);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                getContext().startActivity(i);


            }
        });




        TextView a  = (TextView)listItemView.findViewById(R.id.course);
        a.setText(currentAndroidFlavor.course);
        a.setTypeface(ag);

        TextView b  = (TextView)listItemView.findViewById(R.id.provider);
        b.setText(currentAndroidFlavor.provider);

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

            c.setImageResource(R.drawable.courserag);

        }


        TextView d  = (TextView)listItemView.findViewById(R.id.difficulty);
        d.setText(currentAndroidFlavor.level);




        return listItemView;
    }



}

