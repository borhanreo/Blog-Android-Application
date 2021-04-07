package com.example.blog_android_application.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
@Entity(tableName = "tbl_blog")
public class Blog {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "coverPhoto")
    private String coverPhoto;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "author")
    private String author;

    public Blog(int id, String title, String description, String coverPhoto, String category, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.category = category;
        this.author = author;
    }
    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCoverPhoto() {
        return coverPhoto;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }
}
