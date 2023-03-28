package com.rigosapps.imageorganizer.db.imageItem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rigosapps.memeviewer.model.MemeEntity


// 1
@Database(entities = [MemeEntity::class], version = 1, exportSchema = false)
abstract class MemeDatabase : RoomDatabase() {
    // 2
    abstract fun memeDao(): MemeDao

    // 3
    companion object {
        // 4
        @Volatile
        private var instance: MemeDatabase? = null

        // 5
        fun getInstance(context: Context): MemeDatabase {
            if (instance == null) {
                // 6
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemeDatabase::class.java,
                    "meme_database"
                ).build()
            }
// 7
            return instance as MemeDatabase
        }
    }
}
