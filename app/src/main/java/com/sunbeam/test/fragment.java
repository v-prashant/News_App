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
import android.support.v4.view.ViewPager;
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
import java.util.zip.Inflater;

public class fragment extends Fragment {

    Context context;
    int pos;
    int val;
    public fragment()
    {

    }
    public fragment(int i)
    {
        this.pos = i;
    }

    public static void mymethod(int j)
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return ((MainActivity) getActivity()).my_method();

        return inflater.inflate(R.layout.news,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);

          final TextView editTitle = getView().findViewById(R.id.tittle1);
          final ImageView editUrlImage = getView().findViewById(R.id.image1);
          final TextView editauthor = getView().findViewById(R.id.author);
          final TextView editdate = getView().findViewById(R.id.date);
          final TextView editdescription = getView().findViewById(R.id.description);
          final TextView editcontent = getView().findViewById(R.id.content1);
          final Button editDetails = getView().findViewById(R.id.details);


        String url = "http://newsapi.org/v2/everything?q=bitcoin&from=2020-02-08&sortBy=publishedAt&apiKey=9e1ac8704e124f32ab6b70e9de3a56c8";

        Ion.with(this).load("GET",url).asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                String status = result.get("status").getAsString();

                if(status.equals("ok")){

                        JsonArray array = result.get("articles").getAsJsonArray();
                        JsonObject object = array.get(pos).getAsJsonObject();

                        String author = object.get("author").toString();

                        String title = object.get("title").toString();
                        title = title.substring(1,title.length()-1);

                        String description = object.get("description").toString();

                        String url = object.get("url").toString();
                        url = url.substring(1,url.length()-1);

                        String urlToImage = object.get("urlToImage").toString();
                        urlToImage = urlToImage.substring(1,urlToImage.length()-1);

                        String date = object.get("publishedAt").toString();
                        String content = object.get("content").toString();
                        content = content.substring(1,content.length()-1);

                        JsonObject name1 = object.get("source").getAsJsonObject();
                        String name = name1.get("name").toString();


                        if(name!=null)
                            editTitle.setText(title);
                        if(urlToImage!=null)
                            Picasso.get().load(urlToImage).into(editUrlImage);
                        if(author!=null)
                            editauthor.setText("Author  "+author);
                        if(date!=null)
                            editdate.setText("published at: "+date);
                        if(description!=null)
                            editdescription.setText(description);
                        if(content!=null)
                            editcontent.setText(content);


                    final String finalUrl = url;
                    editDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                                intent.putExtra("url",finalUrl);
                                startActivity(intent);
                            }
                        });

                        Log.e("Prashant","success");
                }
                else{
                    Log.e("Prashant","error");
                }
            }
        });

    }


}
