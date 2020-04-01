package com.example.covid19_navigation;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*public void get_total(){

        //api callback
        //for total number of cases

        Call<cases> call = retrofit_client.getInstance().getapi().latest();
        call.enqueue(new Callback<cases>() {
            @Override
            public void onResponse(Call<cases> call, Response<cases> response) {

                if(!(response.code() ==200)){
                    System.out.println("error ");
                    return;
                }

                cases cases = response.body();
                assert cases != null;
                ArrayList<statewise> arrayList = cases.getStatewiseArrayList();
                active.setText("Active Cases : "+arrayList.get(0).getActive());
                confirmed.setText("Confirmed Cases : "+arrayList.get(0).getConfirmed());
                deaths.setText("Unfortunate Deaths : "+arrayList.get(0).getDeaths());
                recovered.setText("Recoveries : "+arrayList.get(0).getRecovered());



            }

            @Override
            public void onFailure(Call<cases> call, Throwable t) {
                System.out.println("error :" + t.getMessage());

            }
        });

    }

    public void states(View view) {
        //startActivity(new Intent(this,com.example.covid19_navigation.states.class));
    }

    public void timeline(View view) {
        //startActivity(new Intent(this,com.example.covid19_navigation.timeline.class));


    }

    public void tips(){
        String[] name = {"Wash Your Hands frequently!", "It's a LOCKDOWN! Stay Indoors!", "Stand Against FAKE News", "Do Not Panic!", "Stay Strong!We will get through this!",
                "Help the Elderly" , "Groceries and Essentials will be Supplied ", "Be a true Indian, Help those in need"};

        Random random = new Random();
// you have also handle min to max index
        int index = random.nextInt(name.length);
        tv_tips.setText(name[index]);

    }

    public void maps(View view) {
        //startActivity(new Intent(this,com.example.covid19_navigation.GeoJsonDemoActivity.class));
    }*/
}
