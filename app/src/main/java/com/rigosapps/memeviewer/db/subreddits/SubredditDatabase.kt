package com.rigosapps.imageorganizer.db.folder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rigosapps.memeviewer.model.Subreddit


// 1
@Database(entities = [Subreddit::class], version = 1, exportSchema = false)
abstract class SubredditDatabase : RoomDatabase() {
    // 2
    abstract fun subredditDao(): SubredditDao

    // 3
    companion object {
        // 4
        @Volatile
        private var instance: SubredditDatabase? = null

        // 5
        fun getInstance(context: Context): SubredditDatabase {
            if (instance == null) {
                // 6
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubredditDatabase::class.java,
                    "subreddit_database"
                ).build()
            }
// 7
            return instance as SubredditDatabase
        }
    }
}
