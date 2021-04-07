package com.example.blog_android_application.dbholder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.blog_android_application.model.Blog;

import java.util.List;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
@Dao
public interface BlogDao {
    @Query("SELECT * FROM tbl_blog ORDER BY id ASC")
    LiveData<List<Blog>> getAlphabetizedWords();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Blog blog);

    @Query("DELETE FROM tbl_blog")
    void deleteAll();

    @Update
    void update(Blog blog);
}
