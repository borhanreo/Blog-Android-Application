package com.example.blog_android_application.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.blog_android_application.model.Blog;
import com.example.blog_android_application.repository.BlogRepository;

import java.util.List;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogViewModel extends AndroidViewModel {
    private BlogRepository blogRepository;
    private final LiveData<List<Blog>> mBlog;

    public BlogViewModel(Application application) {
        super(application);
        blogRepository = new BlogRepository(application);
        mBlog = blogRepository.getAllWords();
    }

    public LiveData<List<Blog>> getAllBlog() {
        return mBlog;
    }

    public void insert(Blog blog) {
        blogRepository.insert(blog);
    }
    public void update(Blog blog) {
        blogRepository.update(blog);
    }
}
