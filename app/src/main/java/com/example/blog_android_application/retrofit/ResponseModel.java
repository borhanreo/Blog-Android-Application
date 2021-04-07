package com.example.blog_android_application.retrofit;

import com.example.blog_android_application.model.Blog;

import java.util.List;

/**
 * Created by Borhan Uddin on 4/6/2021.
 * borhan.u.cse@gmail.com
 */
public class ResponseModel {
    private List<Blogs> blogs;
    private String message;
    private boolean success;

    public List<Blogs> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blogs> data) {
        this.blogs = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class Blogs{
        private int id;
        private String title;
        private String description;
        private String cover_photo;
        private List<String> categories;
        private Author author;

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

        public String getCover_photo() {
            return cover_photo;
        }

        public void setCover_photo(String cover_photo) {
            this.cover_photo = cover_photo;
        }

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }
    public static class Categories{

    }
    public static class Author{
        private int id;
        private String name;
        private String avatar;
        private String profession;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }
    }
}
