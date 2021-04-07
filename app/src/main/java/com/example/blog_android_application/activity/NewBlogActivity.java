package com.example.blog_android_application.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.blog_android_application.R;
import com.example.blog_android_application.constant.ProjectConstant;
import com.example.blog_android_application.databinding.ActivityNewBlogBinding;
import com.example.blog_android_application.model.Blog;
import com.example.blog_android_application.model.BlogBundleData;
import com.example.blog_android_application.viewmodel.BlogViewModel;
import com.multispinner.MultiSelectSpinner;

import java.util.ArrayList;
import java.util.List;

public class NewBlogActivity extends AppCompatActivity implements MultiSelectSpinner.OnMultipleItemsSelectedListener {
    ActivityNewBlogBinding binding;
    private ArrayList<String> arr = new ArrayList<>();
    String[] array = {"None", "Business", "Lifestyle", "Business-1", "Lifestyle-1", "Business-2", "Lifestyle-2"};
    private BlogViewModel blogViewModel;
    private int from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_blog);
        blogViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(BlogViewModel.class);
        binding.save.setOnClickListener(v->{
            Intent intent = new Intent();
            if(from==1){
                if(TextUtils.isEmpty(binding.id.getText()) || TextUtils.isEmpty(binding.title.getText()) ||
                        TextUtils.isEmpty(binding.desc.getText()) ||
                        TextUtils.isEmpty(binding.category.getSelectedItemsAsString())|| TextUtils.isEmpty(binding.author.getText())){
                    Toast.makeText(this,"TextBox can not be empty", Toast.LENGTH_LONG).show();
                }else {
                    Blog blog = new Blog(Integer.parseInt(binding.id.getText().toString()),binding.title.getText().toString(),
                            binding.desc.getText().toString(),ProjectConstant.D_COVER_PHOTO_URL,binding.category.getSelectedItemsAsString(),
                            binding.author.getText().toString());
                    blogViewModel.update(blog);
                    finish();
                }
            }else{
                if(TextUtils.isEmpty(binding.id.getText()) || TextUtils.isEmpty(binding.title.getText()) ||
                        TextUtils.isEmpty(binding.desc.getText()) || TextUtils.isEmpty(binding.coverPhoto.getText())||
                        TextUtils.isEmpty(binding.category.getSelectedItemsAsString())|| TextUtils.isEmpty(binding.author.getText())){
                    Toast.makeText(this,"TextBox can not be empty", Toast.LENGTH_LONG).show();
                }else {
                    Log.e("borhan",binding.id.getText().toString()+" ");
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_ID, Integer.parseInt(binding.id.getText().toString()));
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_TITLE, binding.title.getText().toString());
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_DESC, binding.desc.getText().toString());
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_COVER_PHOTO, binding.coverPhoto.getText().toString());
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_CATEORY, binding.category.getSelectedItemsAsString());
                    intent.putExtra(ProjectConstant.BUNDLE_KEY_AUTHOR, binding.author.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

        });
        loadCustomes();
        loadMutCheck();
        loadPreviousData();
    }
    public void loadPreviousData(){
        Intent i = getIntent();
        from = i.getIntExtra(ProjectConstant.KEY_FROM,-1);
        if(from==1){
            BlogBundleData blogBundleData = (BlogBundleData) i.getSerializableExtra(ProjectConstant.KEY);
            binding.id.setText(blogBundleData.getId()+"");
            binding.id.setEnabled(false);
            binding.title.setText(blogBundleData.getTitle());
            binding.desc.setText(blogBundleData.getDescription());
            //binding.coverPhoto.setText(blogBundleData.getId()+"");
            //binding.category.setText(blogBundleData.getId());
            binding.author.setText(blogBundleData.getAuthor());
        }

    }
    public void loadCustomes(){
        for(int i=0;i<array.length;i++){
            arr.add(array[i]);
        }
    }
    public void loadMutCheck(){
        binding.category.setItems(arr);
        binding.category.hasNoneOption(true);
        binding.category.setSelection(new int[]{0});
        binding.category.setListener(this);
    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {

    }
}