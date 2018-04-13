package com.example.chandan.demo;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import org.json.JSONObject;

public class UserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private BroadcastReceiver mBroadcastReceiver;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView username, uname;
    NavigationView nav;
    String name[];

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_user_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = (NavigationView) findViewById(R.id.nav_view);
        View header = nav.getHeaderView(0);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(UserProfile.this, Loginactivity.class));
        }
        user = auth.getCurrentUser();
        name = user.getEmail().split("@");

        username = (TextView) header.findViewById(R.id.Username);
        uname = (TextView) header.findViewById(R.id.user);
        username.setText(user.getEmail());

        uname.setText(name[0]);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(UserProfile.this, AddTeams.class));
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (user.getEmail().toString().equals("cttrophy2018@gmail.com")) {

            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.setlive).setVisible(true);
            nav_Menu.findItem(R.id.setfixtures).setVisible(true);
        } else {
            Menu nav_Menu = navigationView.getMenu();
            nav_Menu.findItem(R.id.setlive).setVisible(false);
            nav_Menu.findItem(R.id.setfixtures).setVisible(false);
        }
     


    }

    private void createNotification(String message) {

        Intent i = new Intent(this, livescores.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent p = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder build = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Live Scores")
                .setContentText(message)
                .setSound(notificationSoundURI)
                .setSmallIcon(R.drawable.liveicon)
                .setContentIntent(p);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, build.build());
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
        getMenuInflater().inflate(R.menu.user_profile, menu);
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

        if (id == R.id.myteam) {
            // Handle the camera action
            startActivity(new Intent(UserProfile.this, Myteam.class));
        }
        if (id == R.id.rules) {
            // Handle the camera action
            startActivity(new Intent(UserProfile.this, Rules.class));
        } else if (id == R.id.setfixtures) {
            startActivity(new Intent(UserProfile.this, SetFixtures.class));

        } else if (id == R.id.setlive) {
            startActivity(new Intent(UserProfile.this, SetLive.class));
        } else if (id == R.id.contactus) {
            Button b1;
            TextView t1;
            final Dialog mydialog = new Dialog(this);
            mydialog.setContentView(R.layout.contactus);
            b1 = (Button) mydialog.findViewById(R.id.makecall);
            t1 = (TextView) mydialog.findViewById(R.id.close);
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mydialog.dismiss();
                }

            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mydialog.show();
        } else if (id == R.id.logout) {
            auth.signOut();
            finish();
            startActivity(new Intent(UserProfile.this, Loginactivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        String[] title = {"Teams", "Fixtures", "Matches"};

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    Teams teams = new Teams();
                    return teams;
                case 1:
                    Fixtures fixtures = new Fixtures();
                    return fixtures;
                case 2:
                    Matches matches = new Matches();
                    return matches;
            }
            return null;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
