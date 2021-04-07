package com.example.blog_android_application.dbholder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.blog_android_application.constant.ProjectConstant;
import com.example.blog_android_application.model.Blog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Borhan Uddin on 4/5/2021.

 * borhan.u.cse@gmail.com
 */
@Database(entities = {Blog.class}, version = 1, exportSchema = false)
abstract public class BlogRoomDatabases extends RoomDatabase {
    public abstract BlogDao blogDao();
    private static volatile BlogRoomDatabases INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BlogRoomDatabases getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BlogRoomDatabases.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BlogRoomDatabases.class, "blog_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                BlogDao dao = INSTANCE.blogDao();
                dao.deleteAll();
                /*Blog blog = new Blog(1,"Borhan1","test1", ProjectConstant.D_COVER_PHOTO_URL,"Hello","Hello");
                dao.insert(blog);
                blog = new Blog(2,"Borhan2","test2", ProjectConstant.D_COVER_PHOTO_URL,"Hello","Hello");
                dao.insert(blog);*/
            });
        }
    };
}
