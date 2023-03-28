package com.rigosapps.memeviewer.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rigosapps.imageorganizer.db.folder.SubredditDao
import com.rigosapps.imageorganizer.db.folder.SubredditDatabase
import com.rigosapps.imageorganizer.db.folder.SubredditRepository
import com.rigosapps.memeviewer.model.Meme
import com.rigosapps.memeviewer.model.Subreddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class SubredditViewModel(application: Application) : AndroidViewModel(application) {



    val subreddits: LiveData<List<Subreddit>>

    private val repository: SubredditRepository

    init {

        val subredditDao = SubredditDatabase.getInstance(application).subredditDao()
        repository = SubredditRepository(subredditDao)
        subreddits = repository.readAllData
        Timber.e("Subreddits value: ${subreddits.value}")

    }


    fun addSubreddit(subreddit: Subreddit): Long {
        var id: Long = -1


        val job = viewModelScope.async(Dispatchers.IO) {

            id = repository.addSubreddit(subreddit)

        }

        runBlocking {
            job.join()
        }
        Timber.e("Added new subreddit ${subreddit.title} with id $id")
        return id


    }

    fun deleteSubreddit(subreddit: Subreddit) {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteSubreddit(subreddit)
        }

    }

    fun getSubredit(subredditId: Long): Subreddit {
        lateinit var subreddit: Subreddit
        val job = viewModelScope.launch(Dispatchers.IO) {

            subreddit = repository.getSubreddit(subredditId)
        }

        runBlocking {
            job.join()
        }

        return subreddit
    }


}