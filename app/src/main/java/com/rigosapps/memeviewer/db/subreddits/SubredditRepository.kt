package com.rigosapps.imageorganizer.db.folder

import androidx.lifecycle.LiveData
import com.rigosapps.memeviewer.model.Subreddit

class SubredditRepository(private val subredditDao: SubredditDao) {

    val readAllData: LiveData<List<Subreddit>> = subredditDao.loadAll()

    suspend fun addSubreddit(subreddit: Subreddit): Long {
        return subredditDao.insertSubreddit(subreddit)
    }


    suspend fun deleteSubreddit(subreddit: Subreddit) {
        subredditDao.deleteSubreddit(subreddit)
    }


    suspend fun updateSubreddit(folder: Subreddit) {
        return subredditDao.updateSubreddit(folder)
    }

    suspend fun getSubreddit(id: Long): Subreddit {
        return subredditDao.loadImageItem(id)
    }


}