package com.sunbeam.test;


import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class fragment extends Fragment {


    ArrayList <ModelItems> items = new ArrayList <>();
    ItemAdapter adapter;

    Context context;
    int pos;
    public fragment() {

    }

    public fragment(int i) {

        this.pos = i;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.xmlfragment, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);


        String urltemp="";
        if (pos == 0) {
            urltemp = "http://newsapi.org/v2/top-headlines?sources=vice-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        }
        else if(pos == 1)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=ary-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 2)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 3)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=bbc-sport&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 4)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=usa-today&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 5)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=cnn&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 6)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 7)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=google-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 8)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=the-verge&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else if(pos == 9)
            urltemp = "http://newsapi.org/v2/top-headlines?sources=news24&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        else
        {
            urltemp = "http://newsapi.org/v2/top-headlines?sources=abc-news&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";
        }

            Ion.with(this).load("GET", urltemp).asJsonObject().setCallback(new FutureCallback <JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {

                    String status = result.get("status").getAsString();

                    if (status.equals("ok")) {

                        JsonArray array = result.get("articles").getAsJsonArray();

                        for (int i = 0; i < array.size(); i++) {
                            JsonObject object = array.get(i).getAsJsonObject();

                            String author = object.get("author").toString();

                            String title = object.get("title").toString();
                            title = title.substring(1, title.length() - 1);

                            String url = object.get("url").toString();
                            url = url.substring(1, url.length() - 1);

                            String urlToImage = object.get("urlToImage").toString();
                            urlToImage = urlToImage.substring(1, urlToImage.length() - 1);

                            String date = object.get("publishedAt").toString();
                            String content = object.get("content").toString();
                            content = content.substring(1, content.length() - 1);

                            items.add(new ModelItems(title, author, date, urlToImage, url));


                        }

                        adapter = new ItemAdapter(getActivity(), items);
                        recyclerView.setAdapter(adapter);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(layoutManager);


                        Log.e("Prashant", "success");
                    } else {
                        Log.e("Prashant", "error");
                    }
                }
            });

    }




}