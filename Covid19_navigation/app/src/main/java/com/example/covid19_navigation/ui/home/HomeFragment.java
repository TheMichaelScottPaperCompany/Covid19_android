package com.example.covid19_navigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covid19_navigation.R;
import com.example.covid19_navigation.cases;
import com.example.covid19_navigation.retrofit_client;
import com.example.covid19_navigation.statewise;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView active , confirmed, deaths, date , recovered;
    TextView tv_tips;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        tv_tips = root.findViewById(R.id.tv_tips);
        active =root.findViewById(R.id.active);
        confirmed = root.findViewById(R.id.confirmed);
        deaths  = root.findViewById(R.id.deaths);
        date = root.findViewById(R.id.date);
        recovered = root.findViewById(R.id.recovered);
        tips();
        get_total();

        return root;
    }
    public void get_total(){

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
    }

}