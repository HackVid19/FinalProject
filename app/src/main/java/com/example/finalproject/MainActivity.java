package com.example.finalproject;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LiveupdatesFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_liveupdates);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
    drawer.closeDrawer(GravityCompat.START);
        }else {


            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_liveupdates:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LiveupdatesFragment()).commit();
                break;
            case R.id.nav_precautions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PrecautionFragment()).commit();
                break;
            case R.id.nav_logoff:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LogoutFragment()).commit();
                break;
            case R.id.nav_survey:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SurveyFragment()).commit();
                break;
            case R.id.nav_videocam:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WebcamFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
