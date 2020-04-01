package com.example.covid19_navigation.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19_navigation.R;
import com.example.covid19_navigation.recycler_adapter_states;
import com.example.covid19_navigation.retrofit_client;
import com.example.covid19_navigation.statewise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment implements recycler_adapter_states.OnNoteList  {

    RecyclerView recyclerView;
    ArrayList<statewise> arrayList;
    recycler_adapter_states adapter;


    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        recyclerView = root.findViewById(R.id.recycler_states);
        arrayList = new ArrayList<statewise>();
        Json();
        return root;
    }

    private void Json() {
        Call<String> call = retrofit_client.getInstance().getapi().region();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                assert response.body() != null;
                //Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.code() == 200) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                System.out.println("fail" + t.getMessage());

            }
        });
    }

    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response

            JSONObject obj = new JSONObject(response);
            //obj = response.data;


            arrayList = new ArrayList<statewise>();
            //JSONObject object = obj.getJSONObject("data");
            JSONArray dataArray  = obj.getJSONArray("statewise");
            System.out.println("hello");

            for (int i = 1; i < dataArray.length(); i++) {

                statewise modelRecycler = new statewise();
                JSONObject dataobj = dataArray.getJSONObject(i);

                //modelRecycler.setImgURL(dataobj.getString("imgURL"));
                modelRecycler.setDate(dataobj.getString("lastupdatedtime"));
                modelRecycler.setActive(dataobj.getString("active"));
                modelRecycler.setConfirmed(dataobj.getString("confirmed"));
                modelRecycler.setDeaths(dataobj.getString("deaths"));
                modelRecycler.setRecovered(dataobj.getString("recovered"));
                modelRecycler.setState(dataobj.getString("state"));
                //modelRecycler.setDaily_recovered(dataobj.getString("dailyrecovered"));
                arrayList.add(modelRecycler);

            }

            adapter = new recycler_adapter_states(getContext(),arrayList, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("error" + e.getMessage());
        }

    }

    @Override
    public void OnnoteClick(int position) {
        /*Intent intent = new Intent(states.this, com.example.covid19_india.hospitals_data.class);
        intent.putExtra("selected", modelRecyclerArrayList.get(position).toString());

        intent.putExtra("select", position);
        intent.putExtra("array", modelRecyclerArrayList);
        //intent.putExtra("note",note);

        //intent.getParcelableArrayListExtra("array",arrayList);
        startActivity(intent);*/
        Intent intent = new Intent(getContext(),com.example.covid19_navigation.districts.class);
        intent.putExtra("name",arrayList.get(position).getState());
        startActivity(intent);

        Toast.makeText(getContext(),arrayList.get(position).getState()+"",Toast.LENGTH_SHORT).show();

    }




}