package com.example.blog_android_application.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.blog_android_application.dbholder.BlogViewHolder;
import com.example.blog_android_application.model.Blog;

/**
 * Created by Borhan Uddin on 4/5/2021.
 * borhan.u.cse@gmail.com
 */
public class BlogAdapter extends ListAdapter<Blog, BlogViewHolder> {
    private Context mContext;
    public BlogAdapter(@NonNull DiffUtil.ItemCallback<Blog> diffCallback) {
        super(diffCallback);
    }

    @Override
    public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BlogViewHolder.create(parent);
    }


    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        Blog current = getItem(position);
        holder.bind(current.getId(),current.getTitle(),current.getDescription(),current.getCoverPhoto(),
                current.getCategory(),current.getAuthor());
    }
    public static class BlogNotifier extends DiffUtil.ItemCallback<Blog> {

        @Override
        public boolean areItemsTheSame(@NonNull Blog oldItem, @NonNull Blog newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Blog oldItem, @NonNull Blog newItem) {
            return oldItem.getId()==(newItem.getId());
        }
    }
}
