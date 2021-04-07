package com.example.blog_android_application.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.blog_android_application.R;
import com.example.blog_android_application.constant.ProjectConstant;
import com.example.blog_android_application.databinding.ActivityDetailsBinding;
import com.example.blog_android_application.model.Blog;
import com.example.blog_android_application.model.BlogBundleData;

public class DetailsActivity extends AppCompatActivity{
    ActivityDetailsBinding binding;
    private BlogBundleData blogBundleData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        Intent i = getIntent();
         blogBundleData = (BlogBundleData) i.getSerializableExtra(ProjectConstant.KEY);
        Log.e("borhan", blogBundleData.getAuthor());
        binding.id.setText(blogBundleData.getId()+"");
        binding.title.setText(blogBundleData.getTitle());
        binding.desc.setText(blogBundleData.getDescription());
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.no)
                .error(R.drawable.error);
        Glide.with(this).load(blogBundleData.getCoverPhoto()).apply(options).into(binding.coverPhoto);
        binding.category.setText(blogBundleData.getCategory());
        binding.author.setText(blogBundleData.getAuthor());
        binding.edit.setOnClickListener(v->{
            Log.e("borhan", " click ");
            Intent intent = new Intent(this, NewBlogActivity.class);
            intent.putExtra(ProjectConstant.KEY_FROM, 1);
            intent.putExtra(ProjectConstant.KEY, blogBundleData);
            startActivity(intent);
            finish();
        });
    }


}