package com.rigosapps.memeviewer.viewModels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rigosapps.imageorganizer.db.imageItem.MemeDatabase
import com.rigosapps.imageorganizer.db.imageItem.MemeRepository
import com.rigosapps.memeviewer.helpers.Categories
import com.rigosapps.memeviewer.helpers.MemeToMemeEntity
import com.rigosapps.memeviewer.helpers.NewMemeToMemeEntity
import com.rigosapps.memeviewer.model.MemeEntity
import com.rigosapps.memeviewer.retroFit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class MemeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: MemeRepository


    var memes by mutableStateOf(
        listOf<MemeEntity>()
    )
        private set


    //one of Hot, Top, or Random
    var category by mutableStateOf(
        Categories.HOT
    )


    var currentSubreddit by mutableStateOf("Meme Viewer")
        private set


    var isLoading by mutableStateOf(
        false
    )

    var error by mutableStateOf(
        false
    )
        private set


    init {

        /*check if quote has been generated today. Simplest way is just to store the current day
        in shared preferences and just check if it matches current day. If not must call generateQuuote()

         */


        val job = viewModelScope.async(Dispatchers.IO) {

            val memeDao = MemeDatabase.getInstance(application).memeDao()
            repository = MemeRepository(memeDao)

        }


    }

    fun fetchMemes(subreddit: String, count: Int) {
        when(category){
            Categories.HOT -> fetchHotMemes(subreddit,count)
            Categories.RANDOM -> fetchRandomMemes(subreddit, count)
            Categories.TOP -> fetchTopMemes(subreddit, count)

        }


    }


    fun fetchRandomMemes(subreddit: String, count: Int) {
        isLoading = true
        //must check if in Hot, Random or Top mode and fetch accordingly

        viewModelScope.launch {


            val response = try {
                RetrofitInstance.memeApi.getMemes(subreddit, count)

            } catch (e: IOException) {
                Timber.e("IO Exception with message: $e")
                error = true
                return@launch

            } catch (e: HttpException) {
                Timber.e("Http Exception with message: $e")
                error = true
                return@launch

            }

            if (response.isSuccessful && response.body() != null) {


                memes = response.body()!!.memes.map {
                    MemeToMemeEntity(it)
                }
                currentSubreddit = subreddit

                error = false


            } else {

                error = true
            }
            isLoading = false
        }

    }

    fun fetchHotMemes(subreddit: String, count:Int){

        isLoading = true
        //must check if in Hot, Random or Top mode and fetch accordingly

        viewModelScope.launch {


            val response = try {
                RetrofitInstance.newMemeApi.getHotMemes(subreddit, count)

            } catch (e: IOException) {
                Timber.e("IO Exception with message: $e")
                error = true
                return@launch

            } catch (e: HttpException) {
                Timber.e("Http Exception with message: $e")
                error = true
                return@launch

            }

            if (response.isSuccessful && response.body() != null) {

                val onlyImages = response.body()!!.data.children.filter {

                    it.data.url.endsWith(".gif") ||  it.data.url.endsWith(".png") || it.data.url.endsWith(".jpg") || it.data.url.endsWith(".jpeg") &&
                            !it.data.stickied


                }


                memes = onlyImages.map {

                    NewMemeToMemeEntity(it.data)
                }
                currentSubreddit = subreddit

                error = false


            } else {

                error = true
            }
            isLoading = false
        }


    }

    fun fetchTopMemes(subreddit: String, count: Int){
        isLoading = true
        //must check if in Hot, Random or Top mode and fetch accordingly

        viewModelScope.launch {


            val response = try {
                RetrofitInstance.newMemeApi.getTopMemes(subreddit, count)

            } catch (e: IOException) {
                Timber.e("IO Exception with message: $e")
                error = true
                return@launch

            } catch (e: HttpException) {
                Timber.e("Http Exception with message: $e")
                error = true
                return@launch

            }

            if (response.isSuccessful && response.body() != null) {

                val onlyImages = response.body()!!.data.children.filter {

                    it.data.url.endsWith(".gif") ||  it.data.url.endsWith(".png") || it.data.url.endsWith(".jpg") || it.data.url.endsWith(".jpeg") &&
                            !it.data.stickied


                }


                memes = onlyImages.map {
                    NewMemeToMemeEntity(it.data)
                }
                currentSubreddit = subreddit

                error = false


            } else {

                error = true
            }
            isLoading = false
        }


    }


    fun addMeme(meme: MemeEntity): Long {

        var id: Long = -1


        val job = viewModelScope.async(Dispatchers.IO) {

            id = repository.addMeme(meme)

        }

        runBlocking {
            job.join()
        }
        Timber.e("Added new meme ${meme.title} with id $id")
        return id
    }

    fun fetchFavMemes() {

        val job = viewModelScope.async(Dispatchers.IO) {

            memes = repository.readAllData

        }
        runBlocking {
            job.join()
        }
        Timber.e("Stop here to look at fetched memes.")

    }


}








