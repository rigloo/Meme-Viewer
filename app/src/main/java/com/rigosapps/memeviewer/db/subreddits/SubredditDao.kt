package com.rigosapps.imageorganizer.db.folder

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rigosapps.memeviewer.model.Subreddit

@Dao
interface SubredditDao {

    @Query("SELECT * FROM Subreddit")
    fun loadAll(): LiveData<List<Subreddit>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubreddit(subreddit: Subreddit): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateSubreddit(subreddit: Subreddit)

    @Delete
    fun deleteSubreddit(subreddit: Subreddit)

    @Query("SELECT * FROM Subreddit WHERE key = :subredditId")
    fun loadImageItem(subredditId: Long): Subreddit

}