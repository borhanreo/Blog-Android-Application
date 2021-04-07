package com.example.blog_android_application.model;


import java.io.Serializable;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogBundleData implements Serializable {
    private int id;
    private String title;
    private String description;
    private String coverPhoto;
    private String category;
    private String author;
    public BlogBundleData(int id, String title, String description, String coverPhoto, String category, String author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.category = category;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
