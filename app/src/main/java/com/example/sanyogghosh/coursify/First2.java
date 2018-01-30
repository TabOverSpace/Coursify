package com.example.sanyogghosh.coursify;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class First2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    private InterstitialAd adView;  // The ad
    private AdRequest adRequest;
    private Handler mHandler;       // Handler to display the ad on the UI thread
    private Runnable displayAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);





        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        /*tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.rate_black));*/
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.course_white));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final Coursify_Adapter adapter = new Coursify_Adapter(getSupportFragmentManager(), tabLayout.getTabCount());






        assert viewPager != null;
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        /*tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {

                                                   viewPager.setCurrentItem(tab.getPosition());
                                                   int tabn = tab.getPosition();
                                                   switch (tabn){
                                                       case 0:
                                                           tabLayout.getTabAt(0).setIcon(R.drawable.rate_black);
                                                           tabLayout.getTabAt(1).setIcon(R.drawable.course_white);
                                                           break;
                                                       case 1:
                                                           tabLayout.getTabAt(0).setIcon(R.drawable.rate_white);
                                                           tabLayout.getTabAt(1).setIcon(R.drawable.course_black);
                                                           break;
                                                   }
                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {

                                               }
                                           }


        );
*/

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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first2, menu);
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
            AlertDialog.Builder a = new AlertDialog.Builder(this);

            final TextView ab = new TextView(this);
            ab.setPadding(70,20,20,0);
            final SpannableString s = new SpannableString("https://github.com/TabOverSpace/Coursify");
            Linkify.addLinks(s, Linkify.WEB_URLS);
            ab.setText(s);
            ab.setMovementMethod(LinkMovementMethod.getInstance());
            a.setView(ab);

            a.setTitle("Created by team TabOverSpace");
            final AlertDialog b = a.create();
            a.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     b.dismiss();
                }
            });
            a.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {


            Intent gh = new Intent();
            gh.setAction(Intent.ACTION_SEND);
            gh.setType("text/plain");
            gh.putExtra(Intent.EXTRA_TEXT,"Coursify - your course kiosk ... \n\n"+ Uri.parse("https://play.google.com/store/apps/details?id=com.example.sanyogghosh.com.tab.sanyogghosh.coursify"));
            startActivity(gh);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
