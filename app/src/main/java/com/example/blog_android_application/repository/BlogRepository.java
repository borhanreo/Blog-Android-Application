package com.example.blog_android_application.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.blog_android_application.dbholder.BlogDao;
import com.example.blog_android_application.dbholder.BlogRoomDatabases;
import com.example.blog_android_application.model.Blog;

import java.util.List;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogRepository {
    private BlogDao mBlogDao;
    private LiveData<List<Blog>> mBlog;
    public BlogRepository(Application application) {
        BlogRoomDatabases db = BlogRoomDatabases.getDatabase(application);
        mBlogDao = db.blogDao();
        mBlog = mBlogDao.getAlphabetizedWords();
    }
    public LiveData<List<Blog>> getAllWords() {
        return mBlog;
    }
    public void insert(Blog blog) {
        BlogRoomDatabases.databaseWriteExecutor.execute(() -> {
            mBlogDao.insert(blog);
        });
    }
    public void update(Blog blog){
        BlogRoomDatabases.databaseWriteExecutor.execute(() -> {
            mBlogDao.update(blog);
        });
    }
}
