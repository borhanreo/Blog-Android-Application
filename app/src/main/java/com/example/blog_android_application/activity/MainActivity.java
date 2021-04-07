package com.example.blog_android_application.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.blog_android_application.R;
import com.example.blog_android_application.adapter.BlogAdapter;
import com.example.blog_android_application.constant.ProjectConstant;
import com.example.blog_android_application.dagger.BlogApplication;
import com.example.blog_android_application.databinding.ActivityMainBinding;
import com.example.blog_android_application.model.Blog;
import com.example.blog_android_application.model.BlogBundleData;
import com.example.blog_android_application.retrofit.ApiService;
import com.example.blog_android_application.retrofit.ResponseModel;
import com.example.blog_android_application.viewmodel.BlogViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.HttpURLConnection;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final int activity_result_code = 101;
    private BlogViewModel blogViewModel;
    //Dagger
    @Inject
    ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //dagger initialize
        ((BlogApplication) getApplication()).getAppComponent().inject(this);
        final BlogAdapter adapter = new BlogAdapter(new BlogAdapter.BlogNotifier());
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        blogViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(BlogViewModel.class);

        blogViewModel.getAllBlog().observe(this, blogs -> {
            adapter.submitList(blogs);
        });
        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewBlogActivity.class);
            startActivityForResult(intent, activity_result_code);
        });
        loadData();
    }
    //dagger implement with retrofit api
    private void loadData() {
        apiService.getData().enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    for (int i = 0; i < response.body().getBlogs().size(); i++) {
                        StringBuilder sbCategory = new StringBuilder();
                        StringBuilder sbAuthor = new StringBuilder();
                        for (String str : response.body().getBlogs().get(i).getCategories()) {
                            sbCategory.append(str);
                            sbCategory.append(",");
                        }

                        sbAuthor.append(response.body().getBlogs().get(i).getAuthor().getId());
                        sbAuthor.append(",");
                        sbAuthor.append(response.body().getBlogs().get(i).getAuthor().getName());
                        sbAuthor.append(",");
                        sbAuthor.append(response.body().getBlogs().get(i).getAuthor().getAvatar());
                        sbAuthor.append(",");
                        sbAuthor.append(response.body().getBlogs().get(i).getAuthor().getProfession());

                        Blog blog = new Blog(response.body().getBlogs().get(i).getId(),
                                response.body().getBlogs().get(i).getTitle(),
                                response.body().getBlogs().get(i).getDescription(),
                                response.body().getBlogs().get(i).getCover_photo(),
                                sbCategory.toString(),
                                sbAuthor.toString()
                        );
                        blogViewModel.insert(blog);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == activity_result_code && resultCode == RESULT_OK) {
            Log.e("borhan", data.getIntExtra(ProjectConstant.BUNDLE_KEY_ID, 0) + " ");
            Blog blog = new Blog(data.getIntExtra(ProjectConstant.BUNDLE_KEY_ID, 0),
                    data.getStringExtra(ProjectConstant.BUNDLE_KEY_TITLE),
                    data.getStringExtra(ProjectConstant.BUNDLE_KEY_DESC),
                    data.getStringExtra(ProjectConstant.BUNDLE_KEY_COVER_PHOTO),
                    data.getStringExtra(ProjectConstant.BUNDLE_KEY_CATEORY),
                    data.getStringExtra(ProjectConstant.BUNDLE_KEY_AUTHOR)
            );
            blogViewModel.insert(blog);
        } else {
            Toast.makeText(getApplicationContext(), "insert error ", Toast.LENGTH_LONG).show();
        }
    }
}