package com.example.sanyogghosh.coursify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;



    public class First extends Fragment {
        Spinner a;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final View alone = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_first,container, false);







        a  = (Spinner)alone.findViewById(R.id.spinne);

        FloatingActionButton fab = (FloatingActionButton)alone. findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long ab = a.getSelectedItemPosition();

                int y = (int)ab;

                Log.d("y",y+"");
                if(y==0){

                    Intent o = new Intent(getActivity().getApplicationContext(),Second.class);
                    startActivity(o);
                    getActivity().overridePendingTransition(0,R.anim.fade_in);



                }


                if(y==1){

                    Intent ob = new Intent(getActivity().getApplicationContext(),Third.class);
                    startActivity(ob);
                    getActivity().overridePendingTransition(0,R.anim.fade_in);

                }


            }
        });





        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.course_array1, R.layout.special_dropdown_view);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.special_dropdown);
// Apply the adapter to the spinner
        a.setAdapter(adapter);



        Typeface ag = Typeface.createFromAsset(getContext().getAssets(),"fonts/robotoslabbold.ttf");

        TextView f = (TextView)alone.findViewById(R.id.tech);

        f.setTypeface(ag);





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


}
