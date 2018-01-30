package com.example.sanyogghosh.coursify;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Course_page extends AppCompatActivity {
    private ProgressDialog progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        String provider = b.getString("site");

        WebView a = (WebView)findViewById(R.id.course_from_web);
        WebSettings settings = a.getSettings();
        settings.setJavaScriptEnabled(true);
        a.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);



        progressBar = ProgressDialog.show(Course_page.this, "Course", "Loading Page...");

        a.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


             return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
           }
        });
        a.loadUrl(""+url);
    }

    public void down(View v){

        Intent i = new Intent(this, Fourth.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
        finish();
    }
    public void info(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));

        builder.setMessage("Course Page has been loaded !")
                .setPositiveButton(("CLOSE"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog  = builder.create();
        dialog.show();

    }
}
