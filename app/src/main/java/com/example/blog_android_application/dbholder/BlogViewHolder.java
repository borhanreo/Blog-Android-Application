package com.example.blog_android_application.dbholder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.blog_android_application.R;
import com.example.blog_android_application.activity.DetailsActivity;
import com.example.blog_android_application.constant.ProjectConstant;
import com.example.blog_android_application.model.BlogBundleData;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogViewHolder extends RecyclerView.ViewHolder{
    private final TextView id;
    private final TextView title;
    private final TextView description;
    private final TextView categorie;
    private final TextView author;
    private final ImageView coverphoto;
    private static Context mContext;
    private BlogViewHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.id);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.desc);
        coverphoto = itemView.findViewById(R.id.cover_photo);
        categorie = itemView.findViewById(R.id.category);
        author = itemView.findViewById(R.id.author);
    }

    public void bind(int ids,String titles, String descriptions, String coverphotos, String categories, String authors) {
        id.setText("ID: "+ids);
        title.setText(titles);
        description.setText(descriptions);
        //image view
        categorie.setText(categories);
        author.setText(authors);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.no)
                .error(R.drawable.error);
        Log.e("borhan",coverphotos);
        Glide.with(mContext).load(coverphotos).apply(options).into(coverphoto);
        coverphoto.setOnClickListener(v -> {
            BlogBundleData blogBundleData = new BlogBundleData(ids,titles,descriptions,coverphotos,categories,authors);
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra(ProjectConstant.KEY_FROM,0);
            intent.putExtra(ProjectConstant.KEY, blogBundleData);
            mContext.startActivity(intent);
        });
    }

    public static BlogViewHolder create(ViewGroup parent) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_row, parent, false);
        return new BlogViewHolder(view);
    }
}
