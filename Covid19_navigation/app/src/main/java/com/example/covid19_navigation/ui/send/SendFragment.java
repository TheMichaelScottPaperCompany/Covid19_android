package com.example.covid19_navigation.ui.send;

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
import com.example.covid19_navigation.case_time_series;
import com.example.covid19_navigation.recycler_adapter;
import com.example.covid19_navigation.retrofit_client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendFragment extends Fragment implements recycler_adapter.OnNoteList {

    RecyclerView recyclerView;
    ArrayList<case_time_series> modelRecyclerArrayList;
    recycler_adapter adapter;

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        //final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        recyclerView = root.findViewById(R.id.recycler);


        modelRecyclerArrayList = new ArrayList<case_time_series>();
        Json();
        return root;
    }

    private void Json(){
        Call<String> call = retrofit_client.getInstance().getapi().region();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                assert response.body() != null;
                //Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.code()==200) {
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


            modelRecyclerArrayList = new ArrayList<case_time_series>();
            //JSONObject object = obj.getJSONObject("data");
            JSONArray dataArray  = obj.getJSONArray("cases_time_series");
            System.out.println("hello");

            for (int i = 0; i < dataArray.length(); i++) {

                case_time_series modelRecycler = new case_time_series();
                JSONObject dataobj = dataArray.getJSONObject(i);

                //modelRecycler.setImgURL(dataobj.getString("imgURL"));
                modelRecycler.setDate(dataobj.getString("date"));
                modelRecycler.setTotal_confirmed(dataobj.getString("totalconfirmed"));
                modelRecycler.setTotal_deceased(dataobj.getString("totaldeceased"));
                modelRecycler.setTotal_recovered(dataobj.getString("totalrecovered"));
                modelRecycler.setDaily_confirmed(dataobj.getString("dailyconfirmed"));
                modelRecycler.setDaily_deceased(dataobj.getString("dailydeceased"));
                modelRecycler.setDaily_recovered(dataobj.getString("dailyrecovered"));
                modelRecyclerArrayList.add(modelRecycler);

            }

            adapter = new recycler_adapter(getActivity(),modelRecyclerArrayList, this);
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

        Toast.makeText(getContext(),modelRecyclerArrayList.get(position).getTotal_confirmed()+"",Toast.LENGTH_SHORT).show();

    }
}