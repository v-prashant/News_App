package com.sunbeam.test;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    Context context;

    private final ArrayList <ModelItems> items;

    public ItemAdapter(Context context, ArrayList<ModelItems> items) {
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.news, null);

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        final ModelItems item = items.get(i);



        String title = item.getTitle();
        String author = item.getAuthor();
        String date = item.getDate();
        String urlToImage = item.getUrlToImage();
        final String url = item.getUrl();



                if(title!=null)
                    holder.editTitle.setText(title);
                if(author!=null)
                    holder.editauthor.setText("Author  "+author);
                if(date!=null)
                    holder.editdate.setText("published at: "+date);
                if(urlToImage!=null)
                    Picasso.get().load(urlToImage).into(holder.editUrlImage);


         holder.mybutton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 Intent intent  = new Intent(context,DetailsActivity.class);
                 intent.putExtra("url",url);
                 context.startActivity(intent);
             }
         });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView editTitle,editauthor,editdate;
        ImageView editUrlImage;
        Button mybutton;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

          editTitle = itemView.findViewById(R.id.tittle1);
          editUrlImage = itemView.findViewById(R.id.image1);
          editauthor = itemView.findViewById(R.id.author);
          editdate = itemView.findViewById(R.id.date);
          mybutton = itemView.findViewById(R.id.details);


        }

    }


}