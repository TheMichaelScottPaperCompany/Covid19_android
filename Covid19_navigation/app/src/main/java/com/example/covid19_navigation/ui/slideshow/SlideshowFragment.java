package com.example.covid19_navigation.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
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
import com.example.covid19_navigation.api2;
import com.example.covid19_navigation.articles;
import com.example.covid19_navigation.recycler_adapter_news;
import com.example.covid19_navigation.recycler_adapter_states;
import com.example.covid19_navigation.retrofit_client;
import com.example.covid19_navigation.retrofit_client2;
import com.example.covid19_navigation.statewise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment implements recycler_adapter_news.OnNoteList{
    TextView tv;
    ArrayList<articles> arrayList;
    recycler_adapter_news adapter;
    RecyclerView recyclerView;
    ArrayList<articles> news_array_list;



    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        //final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });
        //tv = root.findViewById(R.id.tv);
        recyclerView = root.findViewById(R.id.recycler_news);



        news();

        return root;
    }

    public void news(){

        Call<String> call = retrofit_client2.getInstance().getapi2().news();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

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
            public void onFailure(Call<String> call, Throwable t) {

                tv.setText("error : " + t.getMessage());

            }
        });
    }

    private void writeRecycler(String response){

        try {
            //getting the whole json object from the response

            JSONObject obj = new JSONObject(response);
            //obj = response.data;


            arrayList = new ArrayList<articles>();
            //JSONObject object = obj.getJSONObject("data");
            JSONArray dataArray  = obj.getJSONArray("articles");
            System.out.println("hello");
            news_array_list = new ArrayList<articles>();

            for (int i = 1; i < dataArray.length(); i++) {

                articles modelRecycler = new articles();
                articles news = new articles();
                JSONObject dataobj = dataArray.getJSONObject(i);

                //modelRecycler.setImgURL(dataobj.getString("imgURL"));
                modelRecycler.setTitle(dataobj.getString("title"));
                modelRecycler.setAuthor(dataobj.getString("author"));
                //modelRecycler.setSources(dataobj.getJSONObject("source"));
                modelRecycler.setDescription(dataobj.getString("description"));
                modelRecycler.setPublishedAt(dataobj.getString("publishedAt"));
                //modelRecycler.setUrl(dataobj.getString("url"));
                news.setUrl(dataobj.getString("url"));

                //modelRecycler.setDescription(dataobj.getString("state"));
                //modelRecycler.setDaily_recovered(dataobj.getString("dailyrecovered"));
                arrayList.add(modelRecycler);
                news_array_list.add(news);

            }

            adapter = new recycler_adapter_news(getContext(),arrayList, (recycler_adapter_news.OnNoteList) this);
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
        try {
            Toast.makeText(getContext(), arrayList.get(position).getTitle() + "", Toast.LENGTH_SHORT).show();
            String inshort = news_array_list.get(position).getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(inshort.toString()));
            startActivity(intent);
        }
        catch (Exception e){
            System.out.println("error : "+ e.getMessage());
        }



    }




}